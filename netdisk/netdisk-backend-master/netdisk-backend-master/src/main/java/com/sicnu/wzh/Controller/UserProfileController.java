// UserProfileController.java
package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Entity.DTO.UserProfileDTO;
import com.sicnu.wzh.Entity.DTO.UserUpdateDTO;
import com.sicnu.wzh.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/user/profile")
@Api(tags = "用户个人中心管理")
public class UserProfileController {
    @Autowired
    private UserService userService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @GetMapping
    @ApiOperation("获取当前用户资料")
    public UserProfileDTO getProfile(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("用户未登录");
        }
        return userService.getUserProfile(userId);
    }
    
    @PutMapping
    @ApiOperation("更新用户资料")
    public String updateProfile(@RequestBody UserUpdateDTO updateDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("用户未登录");
        }
        return userService.updateUserProfile(userId, updateDTO) ? "资料更新成功" : "资料更新失败";
    }
    
    @PostMapping("/avatar")
    @ApiOperation("上传用户头像")
    public String uploadAvatar(@RequestParam("avatar") MultipartFile file, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("用户未登录");
        }
        
        if (file.isEmpty()) {
            throw new RuntimeException("请选择文件");
        }
        
        try {
            // 确保上传目录存在
            Path dir = Paths.get(uploadDir);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = userId + "_avatar" + fileExtension;
            
            // 保存文件
            Path dest = dir.resolve(newFilename);
            file.transferTo(dest.toFile());
            
            // 更新数据库
            String avatarUrl = "/uploads/" + newFilename;
            String result = userService.updateAvatar(userId, avatarUrl);
            
            return result != null ? result : "头像更新失败";
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e);
        }
    }
}