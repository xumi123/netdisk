package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.UserFavorite;
import com.sicnu.wzh.Service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class UserFavoriteController {

    @Autowired
    private UserFavoriteService favoriteService;

    // 添加收藏（匹配服务层 saveFavorite 方法）
    @PostMapping("/add")
    public HttpResonse addFavorite(@RequestBody UserFavorite favorite) {
        // 参数校验（与之前一致）
        if (favorite.getUserId() == null) {
            return HttpResonse.fail().setMsg("用户ID不能为空");
        }
        if (favorite.getFileId() == null && favorite.getFolderId() == null) {
            return HttpResonse.fail().setMsg("文件ID和文件夹ID不能同时为空");
        }
        if (favorite.getFileId() != null && favorite.getFolderId() != null) {
            return HttpResonse.fail().setMsg("文件ID和文件夹ID不能同时存在");
        }

        // 调用服务层保存方法（已定义）
        boolean success = favoriteService.saveFavorite(favorite);
        return success
                ? HttpResonse.success().setMsg("收藏成功")
                : HttpResonse.fail().setMsg("已收藏，请勿重复操作");
    }

    // 移除收藏（匹配服务层 removeFavorite 方法）
    @DeleteMapping("/remove/{favoriteId}")
    public HttpResonse removeFavorite(@PathVariable Long favoriteId) {
        if (favoriteId == null) {
            return HttpResonse.fail().setMsg("收藏ID不能为空");
        }
        boolean success = favoriteService.removeFavorite(favoriteId);
        return success
                ? HttpResonse.success().setMsg("取消收藏成功")
                : HttpResonse.fail().setMsg("收藏记录不存在");
    }

    // 获取用户收藏列表（补充服务层 listByUserId 方法，假设已实现）
    @GetMapping("/list/{userId}")
    public HttpResonse listFavorites(@PathVariable String userId) {
        if (userId == null) {
            return HttpResonse.fail().setMsg("用户ID不能为空");
        }
        // 调用服务层列表查询方法（需确保接口中有此方法，或使用MyBatis-Plus的list）
        return HttpResonse.success()
                .setData(favoriteService.listByUserId(userId))
                .setMsg("查询成功");
    }

    // 检查文件是否已收藏（匹配服务层 getByUserIdAndFile 方法）
    @GetMapping("/check/file")
    public HttpResonse checkFileFavorite(
            @RequestParam String userId,
            @RequestParam String fileId) {
        if (userId == null || fileId == null) {
            return HttpResonse.fail().setMsg("参数不能为空");
        }
        // 调用服务层查询方法（返回null表示未收藏，非null表示已收藏）
        UserFavorite favorite = favoriteService.getByUserIdAndFile(userId, fileId);
        return HttpResonse.success()
                .setData(favorite != null)
                .setMsg("查询成功");
    }

    // 检查文件夹是否已收藏（匹配服务层 getByUserIdAndFolder 方法）
    @GetMapping("/check/folder")
    public HttpResonse checkFolderFavorite(
            @RequestParam String userId,
            @RequestParam String folderId) {
        if (userId == null || folderId == null) {
            return HttpResonse.fail().setMsg("参数不能为空");
        }
        UserFavorite favorite = favoriteService.getByUserIdAndFolder(userId, folderId);
        return HttpResonse.success()
                .setData(favorite != null)
                .setMsg("查询成功");
    }
}