//package com.sicnu.wzh.Service.Impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.sicnu.wzh.Constant.HttpResonse;
//import com.sicnu.wzh.Entity.DTO.UserProfileDTO;
//import com.sicnu.wzh.Entity.DTO.UserUpdateDTO;
//import com.sicnu.wzh.Entity.FolderEntity;
//import com.sicnu.wzh.Entity.User;
//import com.sicnu.wzh.Entity.DTO.User2FileDTO;
//import com.sicnu.wzh.Entity.DTO.User2FolderDTO;
//import com.sicnu.wzh.Mapper.AvatarMapper;
//import com.sicnu.wzh.Mapper.UserMapper;
//import com.sicnu.wzh.Service.FolderService;
//import com.sicnu.wzh.Service.UserService;
//import org.bytedeco.opencv.presets.opencv_core;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.UUID;
//
//@Service
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
//
//    @Autowired
//    private FolderService folderService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public boolean isMembership(String userId) {
//        User user = getById(userId);
//        if (user == null) {
//            return false;
//        }
//        if (user.getUserMembershipExpire() != null && user.getUserMembershipExpire().after(new Date())) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean isAdmin(String userId) {
//        User user = getById(userId);
//        return user.getUserType() == 0 ? false : true;
//    }
//
//
//    @Override
//    public boolean createMainFolder(User user){
//        FolderEntity folder = new FolderEntity();
//        folder.setFolderId(UUID.randomUUID().toString().trim().replaceAll("-",""));
//        folder.setFolderName("Main");
//        Date date = new Date();
//        folder.setCreateTime(date);
//        folder.setUpdateTime(date);
//        folder.setFolderSize(0L);
//        folder.setIsMain(1);
//        folderService.save(folder);
//        User2FolderDTO vo = new User2FolderDTO();
//        vo.setFolderId(folder.getFolderId());
//        vo.setUserId(user.getUserId());
//        vo.setId(UUID.randomUUID().toString().trim().replaceAll("-",""));
//        userMapper.insertUserFolder(vo);
//        return true;
//    }
//
//    @Override
//    public boolean addFile(User2FileDTO vo){
//        if (userMapper.insertUserFile(vo) > 0){
//            return true;
//        }
//        return false;
//    }
//
///********************/
//@Autowired
//private AvatarMapper avatarMapper;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public UserProfileDTO getUserProfile(String userId) {
//        User user = userMapper.selectById(userId);
//        if (user == null) {
//            return null;
//        }
//
//        UserProfileDTO profile = new UserProfileDTO();
//        BeanUtils.copyProperties(user, profile);
//        profile.setMembershipExpire(user.getUserMembershipExpire());
//        profile.setStatus(user.getIsBan());
//
//        // 获取头像
//        String avatar = avatarMapper.findLatestAvatarByUserId(userId);
//        profile.setAvatar(avatar);
//
//        return profile;
//    }
//
//    @Transactional
//    public boolean updateUserProfile(String userId, UserUpdateDTO updateDTO) {
//        User user = userMapper.selectById(userId);
//        if (user == null) {
//            return false;
//        }
//
//        // 验证当前密码
//        if (!passwordEncoder.matches(updateDTO.getCurrentPassword(), user.getPassword())) {
//            throw new RuntimeException("当前密码不正确");
//        }
//
//        // 更新用户名
//        if (!user.getUsername().equals(updateDTO.getUsername())) {
//            user.setUsername(updateDTO.getUsername());
//            userMapper.updateUsername(userId, updateDTO.getUsername());
//        }
//
//        // 更新密码
//        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
//            String encodedPassword = passwordEncoder.encode(updateDTO.getNewPassword());
//            userMapper.updatePassword(userId, encodedPassword);
//        }
//
//        return true;
//    }
//
//    public String updateAvatar(String userId, String avatarLocation) {
//        String avatarId = generateUUID();
//        if (avatarMapper.insertAvatar(avatarId, userId, avatarLocation) > 0) {
//            return avatarLocation;
//        }
//        return null;
//    }
//
//    private String generateUUID() {
//        return java.util.UUID.randomUUID().toString().replace("-", "");
//    }
//
//
//}

package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.DTO.UserProfileDTO;
import com.sicnu.wzh.Entity.DTO.UserUpdateDTO;
import com.sicnu.wzh.Entity.FolderEntity;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.DTO.User2FolderDTO;
import com.sicnu.wzh.Mapper.AvatarMapper;
import com.sicnu.wzh.Mapper.UserMapper;
import com.sicnu.wzh.Service.FolderService;
import com.sicnu.wzh.Service.UserService;
import org.bytedeco.opencv.presets.opencv_core;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Autowired
    private AvatarMapper avatarMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserProfileDTO getUserProfile(String userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return null;
        }

        UserProfileDTO profile = new UserProfileDTO();
        BeanUtils.copyProperties(user, profile);
        profile.setMembershipExpire(user.getUserMembershipExpire());
        profile.setStatus(user.getIsBan());

        // 获取头像
        String avatar = avatarMapper.findLatestAvatarByUserId(userId);
        profile.setAvatar(avatar);

        return profile;
    }

    @Transactional
    public boolean updateUserProfile(String userId, UserUpdateDTO updateDTO) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return false;
        }

        // 验证当前密码
        if (!passwordEncoder.matches(updateDTO.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("当前密码不正确");
        }

        // 更新用户名
        if (!user.getUsername().equals(updateDTO.getUsername())) {
            user.setUsername(updateDTO.getUsername());
            userMapper.updateUsername(userId, updateDTO.getUsername());
        }

        // 更新密码
        if (updateDTO.getNewPassword() != null && !updateDTO.getNewPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(updateDTO.getNewPassword());
            userMapper.updatePassword(userId, encodedPassword);
        }

        return true;
    }

    public String updateAvatar(String userId, String avatarLocation) {
        String avatarId = generateUUID();
        if (avatarMapper.insertAvatar(avatarId, userId, avatarLocation) > 0) {
            return avatarLocation;
        }
        return null;
    }

    private String generateUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }


}
