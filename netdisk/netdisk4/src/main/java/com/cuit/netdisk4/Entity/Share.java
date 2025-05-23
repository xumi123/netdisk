package com.cuit.netdisk4.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 分享记录表实体类，对应数据库中的share_records表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "share_records")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 分享记录ID，自增主键
    private Integer shareId;

    // 分享用户ID，关联users表
    private Integer userId;

    // 文件ID，关联resource_files表
    private Integer fileId;

    // 接收用户ID（可为空，表示公开分享）
    private Integer receiverId;

    // 分享类型：0=公开分享，1=指定用户分享，2=密码分享
    private Integer shareType;

    // 分享密码（如果是密码分享）
    @Column(length = 20)
    private String sharePassword;

    // 访问权限：0=只读，1=可下载，2=可编辑
    private Integer accessPermission;

    // 分享过期时间
    private LocalDateTime expireTime;

    // 分享时间，自动记录创建时间
    private LocalDateTime shareTime;

    @PrePersist
    protected void onCreate() {
        shareTime = LocalDateTime.now();
    }
}