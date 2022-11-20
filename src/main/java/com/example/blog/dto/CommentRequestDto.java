package com.example.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentRequestDto {
    @NotEmpty
    @Size(min = 3, message = "Comment name should have at least 3 characters ")
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Comment name should have at least 10 characters ")
    private String body;
    @NotNull
    private Long postId;
}
