package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.UserFriendRecord;
import com.sicnu.wzh.Entity.VO.FriendVO;

import java.util.List;
import java.util.Map;

public interface UserFriendService extends IService<UserFriendRecord> {

    public List<Map<String,String>> getUserByUsername(String username);

    public List<FriendVO> getFriendVOList(String userId);
}
