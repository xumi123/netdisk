package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.UserFavorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

    // 1. 根据用户ID查询所有收藏
    List<UserFavorite> selectByUserId(String userId);

    // 2. 根据用户ID和文件ID查询收藏记录
    UserFavorite selectByUserIdAndFileId(String userId, String fileId);

    // 3. 根据用户ID和文件夹ID查询收藏记录
    UserFavorite selectByUserIdAndFolderId(String userId, String folderId);

    // 4. 统计用户收藏总数
    Integer countByUserId(String userId);

    // 5. 批量删除用户收藏
    int deleteBatchByUserId(String userId);
}