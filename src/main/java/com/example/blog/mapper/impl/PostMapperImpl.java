package com.example.blog.mapper.impl;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.mapper.GenericMapper;
import com.example.blog.model.Post;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements GenericMapper<PostResponseDto, Post, PostRequestDto> {
    private CommentMapperImpl mapper;

    public PostMapperImpl(CommentMapperImpl mapper) {
        this.mapper = mapper;
    }

    @Override
    public PostResponseDto toDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(post.getId());
        postResponseDto.setTitle(post.getTitle());
        postResponseDto.setDescription(post.getDescription());
        postResponseDto.setContent(post.getContent());
        postResponseDto.setComments(
                post.getComments()
                        .stream()
                        .map(comment -> mapper.toDto(comment))
                        .collect(Collectors.toSet())
        );
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
