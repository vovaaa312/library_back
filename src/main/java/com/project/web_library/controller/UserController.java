package com.project.web_library.controller;

import com.project.web_library.model.request.user_request.UpdateEmailRequest;
import com.project.web_library.model.request.user_request.UpdatePasswordRequest;
import com.project.web_library.model.request.user_request.UpdateRoleRequest;
import com.project.web_library.model.request.user_request.UpdateUsernameRequest;
import com.project.web_library.model.data.user.AuthUser;
import com.project.web_library.service.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
//@PreAuthorize("hasRole('SYSTEM_ADMIN')")

public class UserController {

    private final AuthUserService userService;

   // @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

//    @GetMapping("/getAuthorizedUserDetails")
//    public ResponseEntity<?>getAuthorizedUserDetails(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        auth.getPrincipal();
//        String username = auth.getPrincipal().toString();
//        return ResponseEntity.ok(username);
//       //return ResponseEntity.ok(userService.findAuthUserByUsername(authentication.getUsername()));
//    }

    @PreAuthorize("hasAuthority('admin:create')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody AuthUser user){
        if (userService.findAuthUserByUsername(user.getUsername()).isPresent()) {
            String errorMessage = "User with username: {" + user.getUsername() + "} already exists";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        if(userService.findAuthUserByEmail(user.getEmail()).isPresent()){
            String errorMessage = "User with email: {" + user.getEmail() + "} already exists";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok(userService.updateAuthUser(id,user).orElseThrow());
    }
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody AuthUser user){
        if (userService.findAuthUserByUsername(user.getUsername()).isPresent()) {
            String errorMessage = "User with username: {" + user.getUsername() + "} already exists";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        if(userService.findAuthUserByEmail(user.getEmail()).isPresent()){
            String errorMessage = "User with email: {" + user.getEmail() + "} already exists";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.ok(userService.addUser(user).orElseThrow());
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/updateUsername")
    public ResponseEntity<Optional<?>> updateUsername(@RequestBody UpdateUsernameRequest request) {
        if (userService.findAuthUserById(request.getUserId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.of("User was not found"));
        }
        if (userService.findAuthUserByUsername(request.getNewUsername()).isPresent()) {
            String message = "User with username '" + request.getNewUsername() + "' already exists";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Optional.of(message));

        }

        return ResponseEntity.ok(userService.updateUsername(request.getUserId(), request.getNewUsername()));
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/updateEmail")
    public ResponseEntity<Optional<?>> updateEmail(@RequestBody UpdateEmailRequest request) {
        if (userService.findAuthUserById(request.getUserId()).isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Optional.of("User was not found"));
        }
        if (userService.findAuthUserByEmail(request.getNewEmail()).isPresent()) {
            String message = "User with email '" + request.getNewEmail() + "' already exists";
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Optional.of(message));

        }
        return ResponseEntity
                .ok(userService.updateEmail(request.getUserId(), request.getNewEmail()));
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/updatePassword")
    public ResponseEntity<Optional<?>> updatePassword(@RequestBody UpdatePasswordRequest request) {
        if (userService.findAuthUserById(request.getUserId()).isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Optional.of("User was not found"));
        }

        return ResponseEntity.ok(userService.updatePassword(request.getUserId(), request.getNewPassword()));
    }

    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/updateRole")
    public ResponseEntity<Optional<?>> updateRole(@RequestBody UpdateRoleRequest request) {
        if (userService.findAuthUserById(request.getUserId()).isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Optional.of("User was not found"));
        }

        return ResponseEntity
                .ok(userService
                        .updateRole(request.getUserId(), request.getNewRole()));
    }


    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Optional<?>> deleteUserById(@PathVariable String id) {
        return ResponseEntity.ok(Optional.of(
                userService.deleteUser(id)));
    }

    //@PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<?>> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(Optional.of(
                userService.findAuthUserById(id)));
    }

    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<Optional<?>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(Optional.of(
                userService.findAuthUserByUsername(username)));
    }



}
