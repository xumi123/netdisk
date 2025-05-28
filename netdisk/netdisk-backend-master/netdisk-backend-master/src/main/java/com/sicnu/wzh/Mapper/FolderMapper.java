package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.FolderEntity;
import com.sicnu.wzh.Entity.DTO.Folder2XDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FolderMapper extends BaseMapper<FolderEntity> {

    public List<FolderEntity> getByUserId(String userId);

    public int insertFolderX(Folder2XDTO vo);
}
