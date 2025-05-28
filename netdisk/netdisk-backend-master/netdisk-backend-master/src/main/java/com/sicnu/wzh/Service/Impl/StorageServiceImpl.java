package com.sicnu.wzh.Service.Impl;

import com.sicnu.wzh.Entity.UserStorage;
import com.sicnu.wzh.Mapper.StorageMapper;
import com.sicnu.wzh.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public Long getStorage(String userId) {
        return storageMapper.selectUsedStorageByUserId(userId);
    }

    @Override
    public UserStorage getUserStorage(String userId) {
        return storageMapper.selectUserStorage(userId);
    }
}
