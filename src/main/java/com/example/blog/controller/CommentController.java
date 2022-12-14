package com.example.blog.controller;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.model.CommentResponse;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> create(
            @PathVariable("postId") Long id,
            @RequestBody CommentRequestDto commentRequestDto) {
        commentRequestDto.setPostId(id);
        return new ResponseEntity<>(commentService.create(commentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommentResponse> getAll() {
        return null;
    }

    @GetMapping("comments/{id}")
    public ResponseEntity<CommentResponseDto> getById(@PathVariable("id") Long commentId) {
        return ResponseEntity.ok(commentService.getById(commentId));
    }
}
