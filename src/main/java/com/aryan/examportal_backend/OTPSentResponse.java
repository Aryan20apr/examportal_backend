package com.aryan.examportal_backend;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OTPSentResponse {

    String email;
    String message;
    boolean success;
}
