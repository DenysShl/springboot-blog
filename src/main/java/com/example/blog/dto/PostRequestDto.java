package com.example.blog.dto;

import lombok.Data;

@Data
public class PostRequestDto {
    private String title;
    private String description;
    private String content;
}
