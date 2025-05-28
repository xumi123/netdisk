package com.sicnu.wzh.Entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class PermissionTreeVO {
    private String id;
    private String label;
    private List<PermissionTreeVO> children;
}
