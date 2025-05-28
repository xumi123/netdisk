package com.cuit.netdisk4.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FileResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long parentId;
    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String fileHash;
    private boolean isDirectory;
    private boolean isPublic;
    private String previewUrl;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private boolean deleted;
    private LocalDateTime deleteTime;

    // Getters and Setters
}
