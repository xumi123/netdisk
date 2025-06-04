package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Entity.UserFavorite;
import com.sicnu.wzh.Mapper.UserFavoriteMapper;
import com.sicnu.wzh.Service.UserFavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFavoriteServiceImpl extends ServiceImpl<UserFavoriteMapper, UserFavorite> implements UserFavoriteService {

    @Override
    public UserFavorite getByUserIdAndFile(String userId, String fileId) {
        return lambdaQuery()
                .eq(UserFavorite::getUserId, userId)
                .eq(UserFavorite::getFileId, fileId)
                .one();
    }

    @Override
    public UserFavorite getByUserIdAndFolder(String userId, String folderId) {
        return lambdaQuery()
                .eq(UserFavorite::getUserId, userId)
                .eq(UserFavorite::getFolderId, folderId)
                .one();
    }

    @Override
    public List<UserFavorite> listByUserId(String userId) {
        return lambdaQuery()
                .eq(UserFavorite::getUserId, userId)
                .list();
    }

    @Override
    public boolean saveFavorite(UserFavorite favorite) {
        // 检查唯一性
        if (favorite.getFileId() != null) {
            if (getByUserIdAndFile(favorite.getUserId(), favorite.getFileId()) != null) {
                return false;
            }
        } else if (favorite.getFolderId() != null) {
            if (getByUserIdAndFolder(favorite.getUserId(), favorite.getFolderId()) != null) {
                return false;
            }
        }
        return save(favorite); // 调用ServiceImpl的save方法
    }

    @Override
    public boolean removeFavorite(Long favoriteId) {
        return removeById(favoriteId); // 调用ServiceImpl的removeById方法
    }
}