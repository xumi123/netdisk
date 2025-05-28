package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Config.annotation.CostTime;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Service.AvatarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/avatar")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @CostTime
    @PostMapping("")
    public HttpResonse setAvatar(@Param("userId") String userId,
                                 @Param("avatar") MultipartFile avatar) {
        avatarService.setAvatar(userId,avatar);
        return HttpResonse.success().setMsg("设置头像成功");
    }

    @CostTime
    @GetMapping("/{userId}")
    public void getAvatar(@PathVariable("userId") String userId,
                          HttpServletResponse response) {
        avatarService.sendRecentAvatar(userId,response);
    }

}
