package com.sicnu.wzh.Service.Impl;

import com.sicnu.wzh.Entity.DTO.User2FileDTO;
import com.sicnu.wzh.Entity.FileEntity;
import com.sicnu.wzh.Mapper.RecycleMapper;
import com.sicnu.wzh.Service.FileService;
import com.sicnu.wzh.Service.RecycleService;
import com.sicnu.wzh.Service.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sicnu.wzh.Constant.NSFW.NSFW_BAN;

@Service
public class RecycleServiceImpl implements RecycleService {

    @Autowired
    private RecycleMapper recycleMapper;
    @Autowired
    private VoService voService;

    @Override
    public boolean recycleFile(String fileId) {
        User2FileDTO u2f = recycleMapper.selectU2F(fileId);
        FileEntity file = recycleMapper.selectFileByU2FId(fileId);
        if (file.getIsBan() == NSFW_BAN) {
            return false;
        }
        u2f.setIsDelete(0);
        return recycleMapper.recycleFile(fileId) > 0 ? true : false;
    }


    @Override
    public boolean removeFile(String fileId) {
        return recycleMapper.deleteFile(fileId) > 0 ? true : false;
    }

}
