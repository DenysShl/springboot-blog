package com.example.blog.service;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.model.PostResponse;

public interface PostService extends GenericService<
        PostResponseDto, PostRequestDto, PostResponse> {
}
