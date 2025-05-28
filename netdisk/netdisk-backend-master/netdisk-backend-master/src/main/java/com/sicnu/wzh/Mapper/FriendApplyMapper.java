package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.FriendApplyRecord;
import com.sicnu.wzh.Entity.VO.FriendApplyVO;
import com.sicnu.wzh.Entity.VO.FriendVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendApplyMapper extends BaseMapper<FriendApplyRecord> {

    public List<FriendApplyVO> selectFriendApplyRecordVO(String fromUserId,String targetUserId);

}
