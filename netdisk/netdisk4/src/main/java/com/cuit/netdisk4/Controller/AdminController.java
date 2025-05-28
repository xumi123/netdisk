package com.cuit.netdisk4.Controller;

import com.cuit.netdisk4.Entity.User;
import com.cuit.netdisk4.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 查看用户信息接口
    @GetMapping("/users")
    public ResponseEntity<List<User>> viewUserInfo() {
        List<User> users = adminService.viewUserInfo();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 删除用户信息接口
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable int userId) {
        adminService.deleteUserInfo(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 创建用户信息接口
    @PostMapping("/users")
    public ResponseEntity<User> createUserInfo(@RequestBody User user) {
        User createdUser = adminService.createUserInfo(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 查看用户登录日志接口
    @GetMapping("/users/logs")
    public ResponseEntity<List<User>> viewUserLoginLogs() {
        List<User> users = adminService.viewUserLoginLogs();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}