package com.cuit.netdisk4.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class RecycleBin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long fileId;
    private Long userId;
    private LocalDateTime deleteTime;
    private LocalDateTime expireTime;
    private String deleteReason;

    // Getters and Setters
}
