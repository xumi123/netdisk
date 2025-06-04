package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.UserFavorite;

import java.util.List;

public interface UserFavoriteService extends IService<UserFavorite> {
    // 按用户和文件查询（用于检查收藏状态）
    UserFavorite getByUserIdAndFile(String userId, String fileId);

    // 按用户和文件夹查询（用于检查收藏状态）
    UserFavorite getByUserIdAndFolder(String userId, String folderId);

    // 保存收藏（处理唯一性约束）
    boolean saveFavorite(UserFavorite favorite);

    // 移除收藏
    boolean removeFavorite(Long favoriteId);

    // 查询用户所有收藏
    List<UserFavorite> listByUserId(String userId);
}