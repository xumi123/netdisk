
// UserUpdateDTO.java
package com.sicnu.wzh.Entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("用户更新DTO")
public class UserUpdateDTO {
    @ApiModelProperty(value = "新用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @ApiModelProperty("新密码")
    private String newPassword;
    
    @ApiModelProperty(value = "当前密码", required = true)
    @NotBlank(message = "当前密码不能为空")
    private String currentPassword;
}