package com.example.blog.controller;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(
            @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentById(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @GetMapping("posts/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> getById(@PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getById(commentId));
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto updateComment
                = commentService.updateComment(postId, commentId, commentRequestDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId") Long postId,
                                                    @PathVariable("id") Long id) {
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>(String.format("Comment by id %s - deleted successfully", id),
                HttpStatus.OK);
    }
}
