package com.example.blog.service;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.model.CommentResponse;

public interface CommentService extends GenericService<
        CommentResponseDto, CommentRequestDto, CommentResponse> {
}
