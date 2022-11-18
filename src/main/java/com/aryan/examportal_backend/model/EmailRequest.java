package com.aryan.examportal_backend.model;

import lombok.Data;

@Data
public class EmailRequest {
private String from;
private String message;
private String subject;
}
