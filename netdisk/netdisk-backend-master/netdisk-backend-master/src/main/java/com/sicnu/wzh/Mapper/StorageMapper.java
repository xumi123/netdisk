package com.sicnu.wzh.Mapper;

import com.sicnu.wzh.Entity.UserStorage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper {

    public Long selectUsedStorageByUserId(String userId);

    public UserStorage selectUserStorage(String userId);

}
