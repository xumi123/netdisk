package com.sicnu.wzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sicnu.wzh.Entity.ShareRecord;
import com.sicnu.wzh.Entity.VO.ShareRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShareMapper extends BaseMapper<ShareRecord> {

    public List<ShareRecordVO> selectShareRecordVOByUserId(String userId);

}
