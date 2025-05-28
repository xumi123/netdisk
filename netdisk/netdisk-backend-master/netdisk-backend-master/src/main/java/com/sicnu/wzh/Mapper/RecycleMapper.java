package com.sicnu.wzh.Mapper;

import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RecycleMapper {

    public User2FileDTO selectU2F(String u2fId);

    public FileEntity selectFileByU2FId(String u2fId);

    public int recycleFile(String fileId);

    public int deleteFile(String fileId);

}
