package com.sicnu.wzh.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.FileEntity;
import com.sicnu.wzh.Entity.ShareRecord;
import com.sicnu.wzh.Entity.VO.ShareRecordVO;
import com.sicnu.wzh.Mapper.ShareMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface ShareService extends IService<ShareRecord> {

    public int checkShareStatus(String shareId);

    public List<ShareRecordVO> getShareRecordVOByUserId(String userId);

    public boolean saveFile(String userId, String folderId, String shareId);

    public HttpResonse extractFile(String shareId, String extractCode);

    /**
     *
     * @param u2fId
     * @param autoGenerateEnable
     * @param extractCodeNeeded
     * @param extractCode
     * @return
     */
    public Map<String,String> shareFile(String u2fId,
                                        boolean autoGenerateEnable,
                                        boolean extractCodeNeeded,
                                        String extractCode);

    public String generateShareRecordWithoutExtractCode(String u2fId);

    public String generateExtractCode();
}
