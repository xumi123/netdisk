package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Config.annotation.CostTime;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Service.ExtractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/extract")
public class ExtractController {

    @Autowired
    private ExtractService extractService;

    @CostTime
    @GetMapping("")
    public HttpResonse extractFile(@Param("userId") String userId,
                                   @Param("shareId") String shareId,
                                   @Param("extractCode") String extractCode){
        return extractService.extractFile(userId,shareId,extractCode);
    }

    @CostTime
    @GetMapping("/user")
    public HttpResonse getExtractRecordByUserId(@Param("userId") String userId){
        return HttpResonse.success()
                .setData(extractService.getExtractRecordVOByUserId(userId));
    }
}
