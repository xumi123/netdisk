package com.cuit.netdisk4.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("file_resource")
@ApiModel("文件资源实体")
public class FileResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("文件ID")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("父目录ID(0表示根目录)")
    private Long parentId;

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("文件存储路径")
    private String filePath;

    @ApiModelProperty("文件大小(字节)")
    private Long fileSize;

    @ApiModelProperty("文件哈希值")
    private String fileHash;

    @ApiModelProperty("是否为目录")
    private Boolean isDirectory;

    @ApiModelProperty("是否公开")
    private Boolean isPublic;

    @ApiModelProperty("预览URL")
    private String previewUrl;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除")
    private Boolean deleted;

    @ApiModelProperty("删除时间")
    private Date deleteTime;
}
