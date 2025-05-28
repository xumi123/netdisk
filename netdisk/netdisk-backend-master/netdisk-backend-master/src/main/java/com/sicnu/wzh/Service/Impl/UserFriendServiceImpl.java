package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.UserFriendRecord;
import com.sicnu.wzh.Entity.VO.FriendVO;
import com.sicnu.wzh.Mapper.UserFriendMapper;
import com.sicnu.wzh.Service.UserFriendService;
import com.sicnu.wzh.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFriendServiceImpl extends ServiceImpl<UserFriendMapper, UserFriendRecord> implements UserFriendService {

    @Autowired
    private UserFriendMapper userFriendMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<Map<String,String>> getUserByUsername(String username) {
        List<User> users = userService.list(
                new QueryWrapper<User>()
                        .like("username","%"+username)
                        .or().like("username",username+"%")
                        .or().like("username","%"+username+"%"));
        List<Map<String,String>> res = new ArrayList<>();
        for (User user : users) {
            Map<String,String> userVO = new HashMap<>();
            userVO.put("userId",user.getUserId());
            userVO.put("username",user.getUsername());
            res.add(userVO);
        }
        return res;
    }

    @Override
    public List<FriendVO> getFriendVOList(String userId) {
        return userFriendMapper.selectFriendVOList(userId);
    }
}
