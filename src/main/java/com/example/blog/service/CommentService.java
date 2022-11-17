package com.example.blog.service;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import java.util.List;

public interface CommentService extends GenericService<
        CommentResponseDto, CommentRequestDto> {
    List<CommentResponseDto> getCommentsByPostId(Long postId);

    CommentResponseDto getCommentById(Long postId, Long commentId);

    CommentResponseDto updateComment(Long postId, Long commentId,
                                     CommentRequestDto commentRequestDto);

    void deleteComment(Long postId, Long commentId);
}
