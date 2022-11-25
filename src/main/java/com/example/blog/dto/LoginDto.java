package com.example.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty
    @Size(min = 5, message = "Login should have at least 5 characters")
    private String usernameOrEmail;
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;
}
