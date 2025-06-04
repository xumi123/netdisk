package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.DTO.User2FolderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public int insertUserFolder(User2FolderDTO vo);
    public int insertUserFile(User2FileDTO vo);

    @Update("UPDATE user SET username = #{username}, update_time = NOW() WHERE user_id = #{userId}")
    int updateUsername(@Param("userId") String userId, @Param("username") String username);

    @Update("UPDATE user SET password = #{password}, update_time = NOW() WHERE user_id = #{userId}")
    int updatePassword(@Param("userId") String userId, @Param("password") String password);
}
