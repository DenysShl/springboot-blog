package com.example.blog.service.impl;

import com.example.blog.dto.CommentRequestDto;
import com.example.blog.dto.CommentResponseDto;
import com.example.blog.exception.BlogApiException;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.impl.CommentMapperImpl;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapperImpl commentMapper;
    private final ModelMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              CommentMapperImpl commentMapper, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
        this.mapper = mapper;
    }

    @Override
    public CommentResponseDto create(CommentRequestDto entity) {
        Post post = postRepository.findById(entity.getPostId()).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",
                        String.valueOf(entity.getPostId()))
        );
        Comment comment = commentMapper.toModel(entity);
        comment.setPost(post);
        return mapper.map(commentRepository.save(comment), CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto getById(Long id) {
        return mapper.map(commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(id))
        ), CommentResponseDto.class);
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
        log.info("Successes, update comment by id {}, comment = {}", id, entity);
        return mapper.map(updateComment, CommentResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(id))
        );
        commentRepository.delete(comment);
        log.info("Successes, delete comment by id {}, comment = {}", id, comment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(comment -> mapper.map(comment, CommentResponseDto.class))
                .toList();
    }

    @Override
    public CommentResponseDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",
                        String.valueOf(postId))
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId))
        );
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post ");
        }
        return mapper.map(comment, CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto updateComment(Long postId, Long commentId,
                                            CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",
                        String.valueOf(postId))
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId))
        );
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post ");
        }
        comment.setName(commentRequestDto.getName());
        comment.setEmail(commentRequestDto.getEmail());
        comment.setBody(commentRequestDto.getBody());
        Comment updateComment = commentRepository.save(comment);
        log.info("Successes, update comment by id {} for post by id {}, comment = {}",
                postId, commentId, commentRequestDto);
        return mapper.map(updateComment, CommentResponseDto.class);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id",
                        String.valueOf(postId))
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId))
        );
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post ");
        }
        commentRepository.delete(comment);
    }
}
