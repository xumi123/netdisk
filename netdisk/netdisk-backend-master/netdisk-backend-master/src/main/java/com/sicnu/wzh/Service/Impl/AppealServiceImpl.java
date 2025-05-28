package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicnu.wzh.Entity.AppealRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;
import com.sicnu.wzh.Mapper.AppealMapper;
import com.sicnu.wzh.Service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealServiceImpl extends ServiceImpl<AppealMapper, AppealRecord> implements AppealService {

    @Autowired
    private AppealMapper appealMapper;

    @Override
    public PageInfo<AppealRecordVO> getCheckedAppealRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AppealRecordVO> res = appealMapper.selectCheckedAppealVO(userId,operatorId);
        return new PageInfo<AppealRecordVO>(res);
    }

    @Override
    public PageInfo<AppealRecordVO> getAppealRecordVO(int result, String userId, String operatorId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AppealRecordVO> res = appealMapper.selectAppealVO(result,userId,operatorId);
        return new PageInfo<AppealRecordVO>(res);
    }

    @Override
    public PageInfo<AppealRecordVO> getAppealRecordVO(String userId, String operatorId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AppealRecordVO> res = appealMapper.selectAppealVO(userId,operatorId);
        return new PageInfo<AppealRecordVO>(res);
    }
}
