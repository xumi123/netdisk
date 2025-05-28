package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.ExtractRecord;
import com.sicnu.wzh.Entity.VO.ExtractRecordVO;
import com.sicnu.wzh.Mapper.ExtractMapper;

import java.util.List;

public interface ExtractService extends IService<ExtractRecord> {

    public HttpResonse extractFile(String userId, String shareId, String extractCode);

    public List<ExtractRecordVO> getExtractRecordVOByUserId(String userId);
}
