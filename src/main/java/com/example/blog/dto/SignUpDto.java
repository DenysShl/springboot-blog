package com.example.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {
    @NotEmpty
    @Size(min = 3, message = "First name should have at least 3 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;
    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;
    @Email
    @Size(min = 5, message = "Email should have at least 5 characters")
    private String email;
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;
}
