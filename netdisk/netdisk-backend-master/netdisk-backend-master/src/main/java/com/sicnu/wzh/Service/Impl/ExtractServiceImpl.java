package com.sicnu.wzh.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.ExtractRecord;
import com.sicnu.wzh.Entity.ShareRecord;
import com.sicnu.wzh.Entity.VO.ExtractRecordVO;
import com.sicnu.wzh.Mapper.ExtractMapper;
import com.sicnu.wzh.Service.ExtractService;
import com.sicnu.wzh.Service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sicnu.wzh.Constant.ShareConstant.SHARE_FREE;
import static com.sicnu.wzh.Constant.ShareConstant.SHARE_NEED_EXTRACTCODE;

@Service
public class ExtractServiceImpl extends ServiceImpl<ExtractMapper, ExtractRecord> implements ExtractService {

    @Autowired
    private ShareService shareService;
    @Autowired
    private ExtractMapper extractMapper;

    @Override
    public List<ExtractRecordVO> getExtractRecordVOByUserId(String userId) {
        return extractMapper.selectExtractRecordVO(userId);
    }

    /**
     *
     * @param shareId
     * @param extractCode
     * @return
     */
    @Override
    public HttpResonse extractFile(String userId, String shareId, String extractCode){
        ExtractRecord extractRecord = new ExtractRecord();
        extractRecord.setExtractRecordId(UUID.randomUUID().toString().replaceAll("-",""));
        ShareRecord record = shareService.getById(shareId);
        Map<String,String> res = new HashMap<>();
        if (record == null) {
            return HttpResonse.fail().setMsg("分享记录为空");
        }
        if (record.getShareType() == SHARE_FREE) {
            res.put("fileId",record.getXId());
            extractRecord.setExtractTime(new Date());
            extractRecord.setUserId(userId);
            extractRecord.setShareId(shareId);
            save(extractRecord);
            return HttpResonse.success().setMsg("提取成功").setData(res);
        }
        if (record.getShareType() == SHARE_NEED_EXTRACTCODE) {
            if (record.getExtractCode().equals(extractCode)){
                res.put("fileId",record.getXId());
                extractRecord.setExtractTime(new Date());
                extractRecord.setUserId(userId);
                extractRecord.setShareId(shareId);
                save(extractRecord);
                return HttpResonse.success().setMsg("提取成功").setData(res);
            } else {
                return HttpResonse.fail().setMsg("提取码错误");
            }
        }
        return HttpResonse.fail().setMsg("提取失败");
    }
}
