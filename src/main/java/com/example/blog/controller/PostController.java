package com.example.blog.controller;

import com.example.blog.dto.PostRequestDto;
import com.example.blog.dto.PostResponseDto;
import com.example.blog.model.PostResponse;
import com.example.blog.service.PostService;
import com.example.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "CRUD Rest APIs for Post resources")
@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostResponseDto> create(
            @Valid @RequestBody PostRequestDto postRequestDto) {
        return new ResponseEntity<>(postService.create(postRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Posts REST API")
    @GetMapping
    public ResponseEntity<PostResponse> getAll(
            @RequestParam(
                    value = "pageNo",
                    defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
                    required = false) int pageNo,
            @RequestParam(
                    value = "pageSize",
                    defaultValue = AppConstants.DEFAULT_PAGE_SIZE_NUMBER,
                    required = false) int pageSize,
            @RequestParam(
                    value = "sortBy",
                    defaultValue = AppConstants.DEFAULT_SORT_BY,
                    required = false) String sortBy,
            @RequestParam(
                    value = "sortDir",
                    defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,
                    required = false) String sortDir) {
        return ResponseEntity.ok(postService.getAll(pageNo, pageSize, sortBy, sortDir));
    }

    @ApiOperation(value = "Get Post By Id REST API version 1 with 'headers'")
    @GetMapping(value = "{id}", headers = "X-API-VERSION=1")
    public ResponseEntity<PostResponseDto> getById(@PathVariable(name = "id") Long postId) {
        return ResponseEntity.ok(postService.getById(postId));
    }

    @ApiOperation(value = "Get Post By Id REST API version 2 with 'params'")
    @GetMapping(value = "{id}", params = "version=2")
    public ResponseEntity<PostResponseDto> getByIdV2(@PathVariable(name = "id") Long postId) {
        return ResponseEntity.ok(postService.getById(postId));
    }

    @ApiOperation(value = "Delete Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok("Success, deleted post by id " + id);
    }

    @ApiOperation(value = "Update Post By Id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostResponseDto> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.update(id, postRequestDto));
    }
}
