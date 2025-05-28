package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicnu.wzh.Entity.ReportRecord;
import com.sicnu.wzh.Entity.VO.ReportRecordVO;
import com.sicnu.wzh.Mapper.ReportMapper;
import com.sicnu.wzh.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, ReportRecord> implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public PageInfo<ReportRecordVO> getCheckedReportRecordVO(String userId, String operatorId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReportRecordVO> res = reportMapper.selectCheckedReportVO(userId,operatorId);
        return new PageInfo<ReportRecordVO>(res);
    }

    @Override
    public PageInfo<ReportRecordVO> getReportRecordVO(int result, String userId, String operatorId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReportRecordVO> res = reportMapper.selectReportVO(result,userId,operatorId);
        return new PageInfo<ReportRecordVO>(res);
    }

    @Override
    public PageInfo<ReportRecordVO> getReportRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReportRecordVO> res = reportMapper.selectReportVO(userId,operatorId);
        return new PageInfo<ReportRecordVO>(res);
    }
}
