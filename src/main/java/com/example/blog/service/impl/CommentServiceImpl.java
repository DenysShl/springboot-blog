package com.example.blog.service.impl;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.impl.CommentMapperImpl;
import com.example.blog.model.Comment;
import com.example.blog.model.CommentResponse;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapperImpl commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              CommentMapperImpl commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentResponseDto create(CommentRequestDto entity) {
        Post post = postRepository.findById(entity.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",
                        String.valueOf(entity.getPostId()))
        );
        Comment comment = commentMapper.toModel(entity);
        comment.setPost(post);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public CommentResponseDto getById(Long id) {
        return commentMapper.toDto(commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(id))
        ));
    }

    @Override
    public CommentResponseDto update(Long id, CommentRequestDto entity) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(id))
        );
        comment.setName(entity.getName());
        comment.setEmail(entity.getEmail());
        comment.setBody(entity.getBody());
        Comment updateComment = commentRepository.save(comment);
        log.info("Success updated comment {} on new comment {}", comment, updateComment);
        return commentMapper.toDto(updateComment);
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(id))
        );
        commentRepository.delete(comment);
        log.info("Successes, delete comment by id {}", id);
    }
}
