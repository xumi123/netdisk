package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.FriendApplyRecord;
import com.sicnu.wzh.Entity.VO.FriendApplyVO;

import java.util.List;


public interface FriendApplyService extends IService<FriendApplyRecord> {

    public List<FriendApplyVO> getFriendApplyVO(String fromUserId,String targetUserId);
}
