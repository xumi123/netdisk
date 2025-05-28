package com.sicnu.wzh.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChunkService {
    Integer saveChunk(MultipartFile chunk, String md5, Integer index, Long chunkSize, String resultFileName,String path);

    List<Integer> selectChunkListByMd5(String md5);

    void deleteChunkByMd5(String md5);

    byte[] getChunk(Integer index, Integer chunkSize, String resultFileName,long offset);
}
