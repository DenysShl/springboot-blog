package com.example.blog.mapper.impl;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.mapper.GenericMapper;
import com.example.blog.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements GenericMapper<PostResponseDto, Post, PostRequestDto> {
    @Override
    public PostResponseDto toDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setDescription(post.getDescription());
        postResponseDto.setContent(post.getContent());
        return postResponseDto;
    }

    @Override
    public Post toModel(PostRequestDto postRequestDto) {
        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setDescription(post.getDescription());
        post.setContent(post.getContent());
        return post;
    }
}
