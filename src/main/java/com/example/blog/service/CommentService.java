package com.example.blog.service;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.model.CommentResponse;
import java.util.List;

public interface CommentService extends GenericService<
        CommentResponseDto, CommentRequestDto, CommentResponse> {
    List<CommentResponseDto> getCommentsByPostId(Long postId);

    CommentResponseDto getCommentById(Long postId, Long commentId);
    
}
