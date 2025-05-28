package com.sicnu.wzh.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sicnu.wzh.Entity.ReportRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;
import com.sicnu.wzh.Entity.VO.ReportRecordVO;

import java.util.List;

public interface ReportService extends IService<ReportRecord> {
    public PageInfo<ReportRecordVO> getReportRecordVO(int result, String userId, String operatorId, Integer pageNum, Integer pageSize);
    public PageInfo<ReportRecordVO> getReportRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize);
    public PageInfo<ReportRecordVO> getCheckedReportRecordVO(String userId, String operatorId, Integer pageNum, Integer pageSize);
}
