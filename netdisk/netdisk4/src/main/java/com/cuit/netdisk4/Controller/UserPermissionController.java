package com.cuit.netdisk4.Controller;

import com.cuit.netdisk4.service.UserPermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-permission")
public class UserPermissionController {
    private final UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    // 普通用户转为VIP用户接口
    @PostMapping("/convert-to-vip/{userId}")
    public ResponseEntity<Void> convertToVIP(@PathVariable int userId) {
        userPermissionService.convertToVIP(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 获取VIP剩余时间接口
    @GetMapping("/vip-remaining-time/{userId}")
    public ResponseEntity<Long> getVIPRemainingTime(@PathVariable int userId) {
        long remainingTime = userPermissionService.getVIPRemainingTime(userId);
        return new ResponseEntity<>(remainingTime, HttpStatus.OK);
    }
}