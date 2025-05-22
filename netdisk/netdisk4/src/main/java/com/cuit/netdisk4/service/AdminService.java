package com.cuit.netdisk4.service;

import com.cuit.netdisk4.Entity.User;
import com.cuit.netdisk4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 查看用户信息
    public List<User> viewUserInfo() {
        return userRepository.findAll();
    }

    // 删除用户信息
    public void deleteUserInfo(int userId) {
        userRepository.deleteById(userId);
    }

    // 创建用户信息
    public User createUserInfo(User user) {
        return userRepository.save(user);
    }

    // 查看用户登录日志（这里简单返回所有用户信息，后续可扩展专门的日志表处理）
    public List<User> viewUserLoginLogs() {
        return userRepository.findAll();
    }
}