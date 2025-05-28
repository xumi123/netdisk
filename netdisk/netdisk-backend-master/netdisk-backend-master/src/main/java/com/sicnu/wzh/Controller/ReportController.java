package com.sicnu.wzh.Controller;

import com.sicnu.wzh.Config.annotation.CostTime;
import com.sicnu.wzh.Constant.HttpResonse;
import com.sicnu.wzh.Entity.AppealRecord;
import com.sicnu.wzh.Entity.FileEntity;
import com.sicnu.wzh.Entity.ReportRecord;
import com.sicnu.wzh.Entity.VO.AppealRecordVO;
import com.sicnu.wzh.Entity.VO.ReportRecordVO;
import com.sicnu.wzh.Service.AppealService;
import com.sicnu.wzh.Service.FileService;
import com.sicnu.wzh.Service.ReportService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.sicnu.wzh.Constant.AppealConstant.*;
import static com.sicnu.wzh.Constant.NSFW.NSFW_BAN;
import static com.sicnu.wzh.Constant.NSFW.NSFW_NOT_BAN;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ReportService reportService;

    @RequiresRoles("admin")
    @CostTime
    @GetMapping("/unchecked")
    public HttpResonse getUncheckedReportRecord(@Param("pageNum") Integer pageNum,
                                                @Param("pageSize") Integer pageSize) {
        return HttpResonse.success().setMsg("查询审核记录成功")
                .setData(reportService.getReportRecordVO(APPEAL_UNCHECKED,null,null,pageNum,pageSize));
    }

    @RequiresRoles("admin")
    @CostTime
    @GetMapping("/checked")
    public HttpResonse getCheckedReportRecord(@Param("pageNum") Integer pageNum,
                                              @Param("pageSize") Integer pageSize) {
        return HttpResonse.success().setMsg("查询审核记录成功")
                .setData(reportService.getCheckedReportRecordVO(null,null,pageNum,pageSize));
    }

    @RequiresRoles("admin")
    @CostTime
    @GetMapping("")
    public HttpResonse getAllReportRecord(@Param("pageNum") Integer pageNum,
                                          @Param("pageSize") Integer pageSize) {
        return HttpResonse.success().setMsg("查询审核记录成功")
                .setData(reportService.getReportRecordVO(null,null,pageNum,pageSize));
    }

    @RequiresRoles("admin")
    @CostTime
    @PutMapping("")
    public HttpResonse setResult(@Param("result") int result,
                                 @Param("reportId") String reportId,
                                 @Param("operatorId") String operatorId) {
        ReportRecord reportRecord = reportService.getById(reportId);
        reportRecord.setResult(result);
        reportRecord.setOperatorId(operatorId);
        reportRecord.setOperateTime(new Date());
        if (reportService.updateById(reportRecord)) {
            if (result == APPEAL_REJECT) {
                FileEntity file = fileService.getById(reportRecord.getFileId());
                file.setIsBan(NSFW_BAN);
                if (fileService.updateById(file)) {
                    return HttpResonse.success().setMsg("封禁成功");
                }
                return HttpResonse.fail().setMsg("封禁文件时出现错误，请重试");
            }
            if (result == APPEAL_PASS) {
                FileEntity file = fileService.getById(reportRecord.getFileId());
                file.setIsBan(NSFW_NOT_BAN);
                if (fileService.updateById(file)) {
                    return HttpResonse.success().setMsg("解封成功");
                }
                return HttpResonse.fail().setMsg("解封文件时出现错误，请重试");
            }
        }
        return HttpResonse.fail().setMsg("操作失败");
    }

    @CostTime
    @PostMapping("")
    public HttpResonse saveReportRecord(@Param("fileId") String fileId,
                                        @Param("userId") String userId) {
        FileEntity file = fileService.getFileByU2Fid(fileId);
        ReportRecord record = new ReportRecord();
        record.setFileId(file.getId());
        record.setUserId(userId);
        record.setReportTime(new Date());
        if (reportService.save(record)) {
            return HttpResonse.success().setMsg("举报成功");
        }
        return HttpResonse.fail().setMsg("举报失败，请重新举报");
    }
}
