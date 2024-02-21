package com.hami.photo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("Category")
public class CategoryDto {
    @ApiModelProperty("Category id")
    private UUID categoryId;
    @ApiModelProperty("Category title")
    private String title;
    @ApiModelProperty("Category description")
    private String description;

}
