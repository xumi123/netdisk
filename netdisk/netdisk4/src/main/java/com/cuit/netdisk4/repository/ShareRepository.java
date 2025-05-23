package com.cuit.netdisk4.repository;

import com.cuit.netdisk4.Entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 分享记录仓储接口
 */
@Repository
public interface ShareRepository extends JpaRepository<Share, Integer> {

    /**
     * 根据用户ID查询分享记录
     * @param userId 用户ID
     * @return 分享列表
     */
    List<Share> findByUserId(Integer userId);

    /**
     * 根据接收者ID查询分享记录
     * @param receiverId 接收者ID
     * @return 分享列表
     */
    List<Share> findByReceiverId(Integer receiverId);

    /**
     * 根据文件ID查询分享记录
     * @param fileId 文件ID
     * @return 分享列表
     */
    List<Share> findByFileId(Integer fileId);

    /**
     * 删除过期的分享记录
     * @param currentTime 当前时间
     * @return 删除的记录数
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM com.cuit.netdisk4.Entity.Share s WHERE s.expireTime IS NOT NULL AND s.expireTime < :currentTime")
    int deleteExpiredShares(Date currentTime);
}