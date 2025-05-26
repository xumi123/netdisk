package com.cuit.netdisk4.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.netdisk4.Entity.Share;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分享记录数据访问接口
 */
@Mapper
@Repository
public interface ShareDao extends BaseMapper<Share> {

    /**
     * 根据用户ID查询其创建的所有分享记录
     * @param userId 用户ID
     * @return 分享记录列表
     */
    List<Share> selectByUserId(Integer userId);

    /**
     * 根据接收者ID查询收到的分享记录
     * @param receiverId 接收者ID
     * @return 分享记录列表
     */
    List<Share> selectByReceiverId(Integer receiverId);

    /**
     * 根据文件ID查询分享记录
     * @param fileId 文件ID
     * @return 分享记录列表
     */
    List<Share> selectByFileId(Integer fileId);

    /**
     * 查询指定用户对某个文件的访问权限
     * @param userId 用户ID
     * @param fileId 文件ID
     * @return 访问权限级别
     */
    Integer selectAccessPermission(@Param("userId") Integer userId, @Param("fileId") Integer fileId);

    /**
     * 统计文件的分享次数
     * @param fileId 文件ID
     * @return 分享次数
     */
    Integer countSharesByFileId(Integer fileId);

    /**
     * 删除过期的分享记录
     * @return 删除的记录数
     */
    Integer deleteExpiredShares();
}