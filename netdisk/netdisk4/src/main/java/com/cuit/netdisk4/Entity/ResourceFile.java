package com.cuit.netdisk4.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "resource_files")
@Data
public class ResourceFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    @Column(nullable = false)
    private Integer userId;

    private Integer folderId; // 0表示根目录

    @Column(nullable = false, length = 255)
    private String fileName;

    @Column(nullable = false, length = 512)
    private String filePath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false, length = 50)
    private String fileType;

    @Column(nullable = false)
    private Boolean isPublic = false;

    private String previewUrl;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createTime;

    @Column(insertable = false, columnDefinition = "TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updateTime;

    @Column private Boolean deleted = false; // 新增逻辑删除标记
}

