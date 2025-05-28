package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.UserFriendRecord;
import com.sicnu.wzh.Entity.VO.FriendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserFriendMapper extends BaseMapper<UserFriendRecord> {

    public List<FriendVO> selectFriendVOList(String userId);

}
