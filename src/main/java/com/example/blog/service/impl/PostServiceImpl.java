package com.example.blog.service.impl;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.impl.PostMapperImpl;
import com.example.blog.model.Post;
import com.example.blog.model.PostResponse;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapperImpl postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostMapperImpl postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public PostResponseDto create(PostRequestDto entity) {
        return postMapper.toDto(postRepository.save(postMapper.toModel(entity)));
    }

    @Override
    public PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();

        List<PostResponseDto> content = listOfPosts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());

        return getPostResponse(posts, content);
    }

    private static PostResponse getPostResponse(Page<Post> posts, List<PostResponseDto> content) {
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostResponseDto getById(Long id) {
        return postMapper.toDto(postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        ));
    }

    @Override
    public PostResponseDto update(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        post.setTitle(postRequestDto.getTitle());
        post.setDescription(postRequestDto.getDescription());
        post.setContent(postRequestDto.getContent());
        Post updatePost = postRepository.save(post);
        log.info("Success updated post {} on new post {}", post, updatePost);
        return postMapper.toDto(updatePost);
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        postRepository.delete(post);
        log.info("Success, delete post by id {}", id);
    }
}
