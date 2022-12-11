package com.example.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel(description = "Request Post model information")
@Data
public class PostRequestDto {
    @ApiModelProperty(value = "Blog post title")
    @NotBlank
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @ApiModelProperty(value = "Blog post description")
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @ApiModelProperty(value = "Blog post content")
    @NotEmpty
    @Size(min = 10, message = "Post content should have at least 10 characters")
    private String content;
}
