package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.FolderEntity;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.DTO.User2FolderDTO;
import com.sicnu.wzh.Mapper.UserMapper;
import com.sicnu.wzh.Service.FolderService;
import com.sicnu.wzh.Service.UserService;
import org.bytedeco.opencv.presets.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private FolderService folderService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isMembership(String userId) {
        User user = getById(userId);
        if (user == null) {
            return false;
        }
        if (user.getUserMembershipExpire() != null && user.getUserMembershipExpire().after(new Date())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isAdmin(String userId) {
        User user = getById(userId);
        return user.getUserType() == 0 ? false : true;
    }


    @Override
    public boolean createMainFolder(User user){
        FolderEntity folder = new FolderEntity();
        folder.setFolderId(UUID.randomUUID().toString().trim().replaceAll("-",""));
        folder.setFolderName("Main");
        Date date = new Date();
        folder.setCreateTime(date);
        folder.setUpdateTime(date);
        folder.setFolderSize(0L);
        folder.setIsMain(1);
        folderService.save(folder);
        User2FolderDTO vo = new User2FolderDTO();
        vo.setFolderId(folder.getFolderId());
        vo.setUserId(user.getUserId());
        vo.setId(UUID.randomUUID().toString().trim().replaceAll("-",""));
        userMapper.insertUserFolder(vo);
        return true;
    }

    @Override
    public boolean addFile(User2FileDTO vo){
        if (userMapper.insertUserFile(vo) > 0){
            return true;
        }
        return false;
    }



}
