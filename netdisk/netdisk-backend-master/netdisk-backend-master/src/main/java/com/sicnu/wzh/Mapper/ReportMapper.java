package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.ReportRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;
import com.sicnu.wzh.Entity.VO.ReportRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper extends BaseMapper<ReportRecord> {

    public List<ReportRecordVO> selectReportVO(int result, String userId, String operatorId);
    public List<ReportRecordVO> selectReportVO(String userId, String operatorId);
    public List<ReportRecordVO> selectCheckedReportVO(String userId, String operatorId);
}
