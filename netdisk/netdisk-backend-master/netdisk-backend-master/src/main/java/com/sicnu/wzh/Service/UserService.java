package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Entity.User;
import com.sicnu.wzh.Entity.DTO.User2FileDTO;

public interface UserService extends IService<User> {

    public boolean isMembership(String userId);

    public boolean isAdmin(String userId);

    public boolean createMainFolder(User user);

    public boolean addFile(User2FileDTO vo);

}
