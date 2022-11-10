package com.example.springbootaws.service;

import com.example.springbootaws.dto.PostRequestDto;
import com.example.springbootaws.dto.PostResponseDto;
import com.example.springbootaws.model.PostResponse;

public interface PostService {
    PostResponseDto create(PostRequestDto entity);

    PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    PostResponseDto getById(Long id);

    PostResponseDto update(PostRequestDto postRequestDto, Long id);

    void deleteById(Long id);
}
