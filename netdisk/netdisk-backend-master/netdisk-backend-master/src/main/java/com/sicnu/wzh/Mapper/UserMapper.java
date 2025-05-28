package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.DTO.User2FolderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public int insertUserFolder(User2FolderDTO vo);
    public int insertUserFile(User2FileDTO vo);
}
