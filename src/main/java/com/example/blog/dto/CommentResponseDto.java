package com.example.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Response Comment information")
@Data
public class CommentResponseDto {
    @ApiModelProperty(value = "Comment id")
    private Long id;
    @ApiModelProperty(value = "Comment name")
    private String name;
    @ApiModelProperty(value = "Comment email")
    private String email;
    @ApiModelProperty(value = "Comment body")
    private String body;
    @ApiModelProperty(value = "Comment post id")
    private Long postId;
}
