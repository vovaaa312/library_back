package com.project.web_library.service;

import com.project.web_library.model.request.authentication.AuthRequest;
import com.project.web_library.model.request.authentication.RegisterRequest;
import com.project.web_library.model.response.AuthResponse;
import com.project.web_library.model.data.user.AuthUser;
import com.project.web_library.model.data.user.SystemRole;
import com.project.web_library.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findAuthUserByUsername(request.getUsername()).isPresent()) {
            String errorMessage = "User with username: {" + request.getUsername() + "} already exists";
            throw new UnsupportedOperationException(errorMessage);
        }
        if(userRepository.findAuthUserByEmail(request.getEmail()).isPresent()){
            String errorMessage = "User with email: {" + request.getEmail() + "} already exists";
            throw new UnsupportedOperationException(errorMessage);

        }


        var user = AuthUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .surname(request.getSurname())
                .role(SystemRole.SYSTEM_USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .jwtResponse(jwtToken)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .role(user.getRole())
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                      request.getUsername(),
                      request.getPassword()
              )
         );
         var user = userRepository.findAuthUserByUsername(request.getUsername())
                 .orElseThrow();

         var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .jwtResponse(jwtToken)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .role(user.getRole())
                .build();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return jwtService.isTokenValid(jwt,userDetails);
        }


}
