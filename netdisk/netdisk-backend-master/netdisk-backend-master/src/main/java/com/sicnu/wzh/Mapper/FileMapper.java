package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.FileEntity;
import com.sicnu.wzh.Entity.ThumbnailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface FileMapper extends BaseMapper<FileEntity> {

    public List<FileEntity> selectUncheckedImages();

    public List<FileEntity> selectCheckedImages();

    public List<FileEntity> selectByFolderId(String folderId);

    public FileEntity selectByMd5(String md5);

    public ThumbnailEntity selectThumbnailByPicId(String id);

    public List<FileEntity> selectByUserId(String userId);

    List<FileEntity> searchFilesByContent(@Param("keyword") String keyword);
}
