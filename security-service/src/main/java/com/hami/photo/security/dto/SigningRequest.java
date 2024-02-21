package com.hami.photo.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SigningRequest {
    @ApiModelProperty("User Email for login")
    private String email;
    @ApiModelProperty("User password for login")
    private String password;
}
