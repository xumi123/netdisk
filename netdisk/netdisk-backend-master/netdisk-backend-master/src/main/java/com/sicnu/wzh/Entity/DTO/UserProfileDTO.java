package com.sicnu.wzh.Entity.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户资料DTO")
public class UserProfileDTO {
    @ApiModelProperty("用户ID")
    private String userId;
    
    @ApiModelProperty("用户名")
    private String username;
    
    @ApiModelProperty("头像URL")
    private String avatar;
    
    @ApiModelProperty("网盘容量")
    private Long storage;
    
    @ApiModelProperty("用户类型")
    private Integer userType;
    
    @ApiModelProperty("会员到期时间")
    private Date membershipExpire;
    
    @ApiModelProperty("账号状态：0正常 1禁用")
    private Integer status;
    
    @ApiModelProperty("创建时间")
    private Date createTime;
}