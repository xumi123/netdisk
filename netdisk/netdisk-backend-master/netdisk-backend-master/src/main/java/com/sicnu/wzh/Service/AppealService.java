package com.sicnu.wzh.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sicnu.wzh.Entity.AppealRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;

import java.util.List;

public interface AppealService extends IService<AppealRecord> {
    public PageInfo<AppealRecordVO> getCheckedAppealRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize);
    public PageInfo<AppealRecordVO> getAppealRecordVO(int result, String userId, String operatorId, Integer pageNum, Integer pageSize);
    public PageInfo<AppealRecordVO> getAppealRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize);

}
