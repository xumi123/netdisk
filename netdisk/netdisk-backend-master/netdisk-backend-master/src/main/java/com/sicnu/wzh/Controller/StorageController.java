package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Config.annotation.CostTime;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Service.StorageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @CostTime
    @GetMapping("")
    public HttpResonse getStorage(@Param("userId") String userId){
        return HttpResonse.success().setData(storageService.getUserStorage(userId))
                .setMsg("查询存储空间使用成功");
    }

}
