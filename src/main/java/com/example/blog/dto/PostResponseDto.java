package com.example.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import lombok.Data;

@ApiModel(description = "Response Post model information")
@Data
public class PostResponseDto {
    @ApiModelProperty(value = "Blog post id")
    private Long id;
    @ApiModelProperty(value = "Blog post title")
    private String title;
    @ApiModelProperty(value = "Blog post description")
    private String description;
    @ApiModelProperty(value = "Blog post content")
    private String content;
    @ApiModelProperty(value = "Blog post comments")
    private Set<CommentResponseDto> comments;
}
