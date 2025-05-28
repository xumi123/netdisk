package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.AvatarEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AvatarMapper extends BaseMapper<AvatarEntity> {

    public AvatarEntity selectRecentAvatar(String userId);

}
