package com.aryan.examportal_backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDTO {
    
    String email;
    String password;
    String newpassword;
}
