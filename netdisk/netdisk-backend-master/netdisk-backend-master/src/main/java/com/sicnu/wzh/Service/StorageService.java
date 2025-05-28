package com.sicnu.wzh.Service;

import com.sicnu.wzh.Entity.UserStorage;

public interface StorageService {

    public Long getStorage(String userId);

    public UserStorage getUserStorage(String userId);
}
