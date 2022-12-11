package com.example.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@ApiModel(description = "Request Comment model information")
@Data
public class CommentRequestDto {
    @ApiModelProperty(value = "Comment name")
    @NotEmpty
    @Size(min = 3, message = "Comment name should have at least 3 characters ")
    private String name;

    @ApiModelProperty(value = "Comment email")
    @NotEmpty
    @Email
    private String email;

    @ApiModelProperty(value = "Comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment bode should have at least 10 characters ")
    private String body;

    @ApiModelProperty(value = "Comment post id")
    @NotNull
    private Long postId;
}
