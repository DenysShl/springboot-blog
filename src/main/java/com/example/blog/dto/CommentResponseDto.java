package com.example.blog.dto;

import lombok.Data;

@Data
public class CommentResponseDto {
    private Long id;
    private String name;
    private String email;
    private String body;
    private Long postId;
}
