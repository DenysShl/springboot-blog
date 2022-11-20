package com.example.blog.dto;

import java.util.Set;
import lombok.Data;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentResponseDto> comments;
}
