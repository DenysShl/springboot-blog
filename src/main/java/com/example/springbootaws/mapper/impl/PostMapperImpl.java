package com.example.springbootaws.mapper.impl;

import com.example.springbootaws.dto.PostRequestDto;
import com.example.springbootaws.dto.PostResponseDto;
import com.example.springbootaws.mapper.GenericMapper;
import com.example.springbootaws.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements GenericMapper<PostResponseDto, Post, PostRequestDto> {
    @Override
    public PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getContent()
        );
    }

    @Override
    public Post toModel(PostRequestDto postRequestDto) {
        return new Post(
                postRequestDto.getTitle(),
                postRequestDto.getDescription(),
                postRequestDto.getContent()
        );
    }
}
