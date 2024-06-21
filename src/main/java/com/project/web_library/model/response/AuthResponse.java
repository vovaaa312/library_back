package com.project.web_library.model.response;

import com.project.web_library.model.data.user.AuthUser;
import com.project.web_library.model.data.user.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
     String jwtResponse;
     String userId;
     String username;
     String email;
     String name;
     String surname;
     SystemRole role;

}
