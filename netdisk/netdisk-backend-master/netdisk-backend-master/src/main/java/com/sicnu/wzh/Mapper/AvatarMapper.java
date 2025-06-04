package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.AvatarEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AvatarMapper extends BaseMapper<AvatarEntity> {

    public AvatarEntity selectRecentAvatar(String userId);

    @Select("SELECT avatar_location FROM avatar WHERE user_id = #{userId} ORDER BY create_time DESC LIMIT 1")
    String findLatestAvatarByUserId(String userId);

    @Insert("INSERT INTO avatar (avatar_id, user_id, avatar_location, create_time) " +
            "VALUES (#{avatarId}, #{userId}, #{avatarLocation}, NOW())")
    int insertAvatar(@Param("avatarId") String avatarId,
                     @Param("userId") String userId,
                     @Param("avatarLocation") String avatarLocation);
}
