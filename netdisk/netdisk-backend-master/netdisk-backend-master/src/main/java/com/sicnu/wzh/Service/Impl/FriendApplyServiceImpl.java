package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Entity.FriendApplyRecord;
import com.sicnu.wzh.Entity.VO.FriendApplyVO;
import com.sicnu.wzh.Entity.VO.FriendVO;
import com.sicnu.wzh.Mapper.FriendApplyMapper;
import com.sicnu.wzh.Service.FriendApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendApplyServiceImpl extends ServiceImpl<FriendApplyMapper, FriendApplyRecord> implements FriendApplyService {

    @Autowired
    private FriendApplyMapper friendApplyMapper;

    public List<FriendApplyVO> getFriendApplyVO(String fromUserId, String targetUserId) {
        return friendApplyMapper.selectFriendApplyRecordVO(fromUserId,targetUserId);
    }
}
