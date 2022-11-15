package com.example.blog.mapper.impl;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.mapper.GenericMapper;
import com.example.blog.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements GenericMapper<
        CommentResponseDto, Comment, CommentRequestDto> {
    @Override
    public CommentResponseDto toDto(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setId(comment.getId());
        commentResponseDto.setName(comment.getName());
        commentResponseDto.setEmail(comment.getEmail());
        commentResponseDto.setBody(comment.getBody());
        commentResponseDto.setPostId(comment.getPost().getId());
        return commentResponseDto;
    }

    @Override
    public Comment toModel(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setName(commentRequestDto.getName());
        comment.setEmail(commentRequestDto.getEmail());
        comment.setBody(commentRequestDto.getBody());
        return comment;
    }
}
