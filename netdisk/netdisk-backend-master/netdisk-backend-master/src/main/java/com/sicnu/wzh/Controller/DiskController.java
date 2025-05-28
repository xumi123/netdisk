package com.sicnu.wzh.Controller;


import com.sicnu.wzh.Config.annotation.CostTime;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.VO.EntityTreeVO;
import com.sicnu.wzh.Service.DiskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.sicnu.wzh.Constant.MinetypeConstant.*;

/**
 * @author hanaue
 */
@RestController
@RequestMapping("/api/disk")
public class DiskController {

    @Autowired
    private DiskService diskService;

    @GetMapping("/{userId}")
    @CostTime
    public HttpResonse getFolderAndFileTree(@PathVariable("userId") String userId,
                                            @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTree(userId,folderId);
        return HttpResonse.success().setData(result).setMsg("查询文件-文件夹树");
    }

    @CostTime
    @GetMapping("/")
    public HttpResonse getFileAndFolderByFolderId(@Param("folderId") String folderId) {
        List<EntityTreeVO> result = diskService.getFolderAndFileByFolderId(folderId);
        return HttpResonse.success().setMsg("查询文件夹下文件成功").setData(result);
    }

    @CostTime
    @GetMapping("/folder/{userId}")
    public HttpResonse getFolderTree(@PathVariable("userId") String userId){
        EntityTreeVO result = diskService.createFolderTree(userId);
        return HttpResonse.success().setData(result).setMsg("查询文件夹树成功");
    }

    @CostTime
    @GetMapping("/image/{userId}")
    public HttpResonse getImageFolderAndFile(@PathVariable("userId") String userId,
                                             @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTreeByType(userId,folderId,
                                                                        MINETYPE_IMAGE);
        return HttpResonse.success().setData(result).setMsg("查询图片成功");
    }

    @CostTime
    @GetMapping("/document/{userId}")
    public HttpResonse getDocumentFolderAndFile(@PathVariable("userId") String userId,
                                                @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTreeByType(userId,folderId,
                                                                        MINETYPE_DOCUMENT);
        return HttpResonse.success().setData(result).setMsg("查询文档成功");
    }

    @CostTime
    @GetMapping("/video/{userId}")
    public HttpResonse getVideoFolderAndFile(@PathVariable("userId") String userId,
                                             @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTreeByType(userId,folderId,
                                                                        MINETYPE_VIDEO);
        return HttpResonse.success().setData(result).setMsg("查询视频成功");
    }

    @CostTime
    @GetMapping("/music/{userId}")
    public HttpResonse getMusicFolderAndFile(@PathVariable("userId") String userId,
                                             @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTreeByType(userId,folderId,
                                                                        MINETYPE_MUSIC);
        return HttpResonse.success().setData(result).setMsg("查询音乐成功");
    }

    @CostTime
    @GetMapping("/execute/{userId}")
    public HttpResonse getExecutFolderAndFile(@PathVariable("userId") String userId,
                                              @Param("folderId") String folderId){
        EntityTreeVO result = diskService.createFolderAndFileTreeByType(userId,folderId,
                                                                          MINETYPE_EXE);
        return HttpResonse.success().setData(result).setMsg("查询应用成功");
    }
}
