package com.hami.photo.dto;

import com.hami.photo.common.entity.category.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@ApiModel("Photo")
public class PhotoDto {
    @ApiModelProperty("Photo id")
    private UUID photo_id;
    @ApiModelProperty("Title")
    private String title;
    @ApiModelProperty("Description")
    private String description;
    @ApiModelProperty("Image")
    private String image;
    @ApiModelProperty("Address")
    private String address;
    @ApiModelProperty("Category")
    private UUID category;

}
