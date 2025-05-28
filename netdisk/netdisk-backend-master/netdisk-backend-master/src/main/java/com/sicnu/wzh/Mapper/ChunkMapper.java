package com.sicnu.wzh.Mapper;

import com.sicnu.wzh.Entity.PO.ChunkPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChunkMapper {
    List<ChunkPO> selectChunkListByMd5(String md5);

    Integer insertChunk(ChunkPO chunkPO);

    void deleteChunkByMd5(String md5);
}
