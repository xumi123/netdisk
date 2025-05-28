package com.cuit.netdisk4.service;

import com.cuit.netdisk4.Entity.Share;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 分享服务接口
 */
@Service
public interface ShareService {

    /**
     * 创建新的分享记录
     * @param share 分享实体
     * @return 分享ID
     */
    Integer createShare(Share share);

    /**
     * 根据ID获取分享记录
     * @param shareId 分享ID
     * @return 分享实体
     */
    Share getShareById(Integer shareId);

    /**
     * 根据文件ID获取分享记录列表
     * @param fileId 文件ID
     * @return 分享列表
     */
    List<Share> getSharesByFileId(Integer fileId);

    /**
     * 根据用户ID获取其创建的分享记录
     * @param userId 用户ID
     * @return 分享列表
     */
    List<Share> getSharesByUserId(Integer userId);

    /**
     * 根据接收者ID获取收到的分享记录
     * @param receiverId 接收者ID
     * @return 分享列表
     */
    List<Share> getSharesByReceiverId(Integer receiverId);

    /**
     * 验证分享链接有效性
     * @param shareId 分享ID
     * @param password 分享密码（如果有）
     * @return 验证结果（包含分享信息和权限）
     */
    Map<String, Object> validateShareLink(Integer shareId, String password);

    /**
     * 更新分享信息
     * @param share 分享实体
     * @return 更新结果
     */
    Boolean updateShare(Share share);

    /**
     * 删除分享记录
     * @param shareId 分享ID
     * @return 删除结果
     */
    Boolean deleteShare(Integer shareId);

    /**
     * 批量删除分享记录
     * @param shareIds 分享ID列表
     * @return 删除结果
     */
    Boolean batchDeleteShares(List<Integer> shareIds);

    /**
     * 清理过期的分享记录
     * @return 清理的记录数
     */
    Integer cleanExpiredShares();
}