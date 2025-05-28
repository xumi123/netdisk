package com.cuit.netdisk4.service;

import com.cuit.netdisk4.Entity.User;
import com.cuit.netdisk4.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class UserPermissionService {
    private final UserRepository userRepository;

    public UserPermissionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 普通用户转为VIP用户
    public void convertToVIP(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // 假设设置VIP有效期为1个月（这里简单设置，实际可根据业务调整）
            long oneMonthInMillis = 30 * 24 * 60 * 60 * 1000;
            Timestamp vipExpiry = new Timestamp(Instant.now().toEpochMilli() + oneMonthInMillis);
            user.setIsVip(true);
            user.setVipExpiry(vipExpiry);
            userRepository.save(user);
        }
    }

    // VIP用户转为普通用户（假设定期检查，这里简化为一个方法）
    public void checkAndConvertVIPToNormal() {
        List<User> vipUsers = userRepository.findByIsVip(true);
        Timestamp currentTime = new Timestamp(Instant.now().toEpochMilli());
        for (User user : vipUsers) {
            if (user.getVipExpiry() != null && user.getVipExpiry().before(currentTime)) {
                user.setIsVip(false);
                user.setVipExpiry(null);
                userRepository.save(user);
            }
        }
    }

    // 获取VIP剩余时间
    public long getVIPRemainingTime(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.isVip()) {
            Timestamp vipExpiry = user.getVipExpiry();
            if (vipExpiry != null) {
                return vipExpiry.getTime() - Instant.now().toEpochMilli();
            }
        }
        return 0;
    }
}