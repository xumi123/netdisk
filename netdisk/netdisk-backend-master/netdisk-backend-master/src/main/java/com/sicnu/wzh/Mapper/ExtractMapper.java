package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.ExtractRecord;
import com.sicnu.wzh.Entity.VO.ExtractRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExtractMapper extends BaseMapper<ExtractRecord> {

    public List<ExtractRecordVO> selectExtractRecordVO(String userId);

}
