package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.AppealRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppealMapper extends BaseMapper<AppealRecord> {
    public List<AppealRecordVO> selectAppealVO(int result, String userId, String operatorId);
    public List<AppealRecordVO> selectAppealVO(String userId, String operatorId);
    public List<AppealRecordVO> selectCheckedAppealVO(String userId,String operatorId);
}
