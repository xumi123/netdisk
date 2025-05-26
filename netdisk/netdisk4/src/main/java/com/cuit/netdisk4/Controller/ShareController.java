package com.cuit.netdisk4.Controller;

import com.cuit.netdisk4.Entity.Share;
import com.cuit.netdisk4.service.ShareService;
import com.cuit.netdisk4.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 分享功能控制器
 */
@RestController
@RequestMapping("/api/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /**
     * 创建分享
     * @param share 分享信息
     * @return 分享ID
     */
    @PostMapping("/create")
    public Result createShare(@RequestBody Share share) {
        Integer shareId = shareService.createShare(share);
        if (shareId != null) {
            return Result.success(shareId);
        }
        return Result.error("创建分享失败");
    }

    /**
     * 根据ID获取分享信息
     * @param shareId 分享ID
     * @return 分享信息
     */
    @GetMapping("/{shareId}")
    public Result getShareById(@PathVariable Integer shareId) {
        Share share = shareService.getShareById(shareId);
        if (share != null) {
            return Result.success(share);
        }
        return Result.error("分享不存在");
    }

    /**
     * 获取用户创建的所有分享
     * @param userId 用户ID
     * @return 分享列表
     */
    @GetMapping("/user/{userId}")
    public Result getSharesByUserId(@PathVariable Integer userId) {
        List<Share> shares = shareService.getSharesByUserId(userId);
        return Result.success(shares);
    }

    /**
     * 获取用户收到的所有分享
     * @param receiverId 用户ID
     * @return 分享列表
     */
    @GetMapping("/received/{receiverId}")
    public Result getSharesByReceiverId(@PathVariable Integer receiverId) {
        List<Share> shares = shareService.getSharesByReceiverId(receiverId);
        return Result.success(shares);
    }

    /**
     * 获取文件的所有分享
     * @param fileId 文件ID
     * @return 分享列表
     */
    @GetMapping("/file/{fileId}")
    public Result getSharesByFileId(@PathVariable Integer fileId) {
        List<Share> shares = shareService.getSharesByFileId(fileId);
        return Result.success(shares);
    }

    /**
     * 验证分享链接
     * @param shareId 分享ID
     * @param password 分享密码
     * @return 验证结果
     */
    @PostMapping("/validate")
    public Result validateShareLink(@RequestParam Integer shareId,
                                    @RequestParam(required = false) String password) {
        Map<String, Object> result = shareService.validateShareLink(shareId, password);
        if ((Boolean) result.get("valid")) {
            return Result.success(result);
        }
        return Result.error(result.get("message").toString());
    }

    /**
     * 更新分享信息
     * @param share 分享信息
     * @return 更新结果
     */
    @PutMapping("/update")
    public Result updateShare(@RequestBody Share share) {
        Boolean success = shareService.updateShare(share);
        if (success) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    /**
     * 删除分享
     * @param shareId 分享ID
     * @return 删除结果
     */
    @DeleteMapping("/{shareId}")
    public Result deleteShare(@PathVariable Integer shareId) {
        Boolean success = shareService.deleteShare(shareId);
        if (success) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

    /**
     * 批量删除分享
     * @param shareIds 分享ID列表
     * @return 删除结果
     */
    @DeleteMapping("/batch")
    public Result batchDeleteShares(@RequestBody List<Integer> shareIds) {
        Boolean success = shareService.batchDeleteShares(shareIds);
        if (success) {
            return Result.success();
        }
        return Result.error("批量删除失败");
    }
}