package com.hami.photo.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Signup")
public class SignupRequest {
    @ApiModelProperty("User first name")
    private String firstName;
    @ApiModelProperty("User last name")
    private String lastName;
    @ApiModelProperty("User Email")
    private String email;
    @ApiModelProperty("User password")
    private String password;
    @ApiModelProperty("User enabled")
    private boolean enabled;
}
