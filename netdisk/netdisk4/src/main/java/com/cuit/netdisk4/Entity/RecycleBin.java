package com.cuit.netdisk4.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "recycle_bin")
@Data
public class RecycleBin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recycleId;

    @Column(nullable = false)
    private Integer fileId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // 修正插入配置
    private Timestamp deleteTime;
}


