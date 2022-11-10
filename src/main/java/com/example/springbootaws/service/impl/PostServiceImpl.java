package com.example.springbootaws.service.impl;

import com.example.springbootaws.dto.PostRequestDto;
import com.example.springbootaws.dto.PostResponseDto;
import com.example.springbootaws.exception.ResourceNotFoundException;
import com.example.springbootaws.mapper.impl.PostMapperImpl;
import com.example.springbootaws.model.Post;
import com.example.springbootaws.model.PostResponse;
import com.example.springbootaws.repository.PostRepository;
import com.example.springbootaws.service.PostService;
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
    private PostRepository postRepository;
    private PostMapperImpl postMapper;

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
                .map(post -> postMapper.toDto(post))
                .collect(Collectors.toList());

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
    public PostResponseDto update(PostRequestDto postRequestDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        post.setTitle(postRequestDto.getTitle());
        post.setDescription(postRequestDto.getDescription());
        post.setTitle(postRequestDto.getTitle());
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(id))
        );
        postRepository.delete(post);
        log.info("Success, delete student by id {}", id);
    }
}
