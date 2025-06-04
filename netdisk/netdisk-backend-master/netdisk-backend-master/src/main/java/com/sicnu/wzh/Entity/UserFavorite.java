package com.sicnu.wzh.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_favorites") // 修正表名
public class UserFavorite {
    @TableId("favorite_id")
    private Long favoriteId; // 修正数据类型为Long
    @TableField("user_id")
    private String userId;
    @TableField("file_id")
    private String fileId; // 修正字段名（原为friendId，现已修正）
    @TableField("folder_id")
    private String folderId;
    @TableField("create_time")
    private Date createTime;
}