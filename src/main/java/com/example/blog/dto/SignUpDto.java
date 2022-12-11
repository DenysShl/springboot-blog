package com.example.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel(description = "Register user 'Sign-Up Dto'")
@Data
public class SignUpDto {
    @ApiModelProperty(value = "Process registering, first name")
    @NotEmpty
    @Size(min = 3, message = "First name should have at least 3 characters")
    private String firstName;

    @ApiModelProperty(value = "Process registering, last name")
    @NotEmpty
    @Size(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;

    @ApiModelProperty(value = "Process registering, username")
    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    private String username;

    @ApiModelProperty(value = "Process registering, email")
    @Email
    @Size(min = 5, message = "Email should have at least 5 characters")
    private String email;

    @ApiModelProperty(value = "Process registering, password")
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;
}
