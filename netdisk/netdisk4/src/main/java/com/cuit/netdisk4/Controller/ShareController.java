package com.cuit.netdisk4.Controller;

import com.cuit.netdisk4.common.Result;
import com.cuit.netdisk4.Entity.Share;
import com.cuit.netdisk4.service.ShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/share")
@Tag(name = "分享管理", description = "文件分享相关接口")
@SecurityRequirement(name = "BearerAuth")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @PostMapping("/create")
    @Operation(summary = "创建分享", description = "创建文件分享链接，支持公开分享、指定用户分享和密码分享")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "创建成功",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "500", description = "创建失败，可能原因：文件不存在、参数错误")
    })
    public Result createShare(@RequestBody
                              @Schema(description = "分享信息", required = true) Share share) {
        Integer shareId = shareService.createShare(share);
        if (shareId != null) {
            return Result.success(shareId);
        }
        return Result.error("创建分享失败");
    }

    @GetMapping("/{shareId}")
    @Operation(summary = "获取分享详情", description = "根据分享ID获取分享信息")
    @Parameters({
            @Parameter(name = "shareId", description = "分享ID", required = true, in = ParameterIn.PATH, example = "123")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(schema = @Schema(implementation = Share.class))),
            @ApiResponse(responseCode = "404", description = "分享不存在")
    })
    public Result getShareById(@PathVariable Integer shareId) {
        Share share = shareService.getShareById(shareId);
        if (share != null) {
            return Result.success(share);
        }
        return Result.error("分享不存在");
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户创建的分享", description = "查询指定用户创建的所有分享记录")
    @Parameters({
            @Parameter(name = "userId", description = "用户ID", required = true, in = ParameterIn.PATH, example = "1001")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Share.class))))
    })
    public Result getSharesByUserId(@PathVariable Integer userId) {
        List<Share> shares = shareService.getSharesByUserId(userId);
        return Result.success(shares);
    }

    @GetMapping("/received/{receiverId}")
    @Operation(summary = "获取用户收到的分享", description = "查询指定用户作为接收者收到的所有分享记录")
    @Parameters({
            @Parameter(name = "receiverId", description = "接收者ID", required = true, in = ParameterIn.PATH, example = "1002")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Share.class))))
    })
    public Result getSharesByReceiverId(@PathVariable Integer receiverId) {
        List<Share> shares = shareService.getSharesByReceiverId(receiverId);
        return Result.success(shares);
    }

    @GetMapping("/file/{fileId}")
    @Operation(summary = "获取文件的分享记录", description = "查询指定文件的所有分享记录")
    @Parameters({
            @Parameter(name = "fileId", description = "文件ID", required = true, in = ParameterIn.PATH, example = "5001")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "查询成功",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Share.class))))
    })
    public Result getSharesByFileId(@PathVariable Integer fileId) {
        List<Share> shares = shareService.getSharesByFileId(fileId);
        return Result.success(shares);
    }

    @PostMapping("/validate")
    @Operation(summary = "验证分享链接", description = "验证分享链接的有效性，需要提供分享ID和密码（如果有）")
    @Parameters({
            @Parameter(name = "shareId", description = "分享ID", required = true, example = "123"),
            @Parameter(name = "password", description = "分享密码（如果需要）", required = false, example = "123456")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "验证成功",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "400", description = "参数错误"),
            @ApiResponse(responseCode = "403", description = "密码错误或无权限"),
            @ApiResponse(responseCode = "404", description = "分享不存在或已过期")
    })
    public Result validateShareLink(@RequestParam Integer shareId,
                                    @RequestParam(required = false) String password) {
        Map<String, Object> result = shareService.validateShareLink(shareId, password);
        if ((Boolean) result.get("valid")) {
            return Result.success(result);
        }
        return Result.error(result.get("message").toString());
    }

    @PutMapping("/update")
    @Operation(summary = "更新分享信息", description = "更新已存在的分享记录，如修改过期时间、访问权限等")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "更新成功",
                    content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "分享不存在"),
            @ApiResponse(responseCode = "403", description = "无权限修改")
    })
    public Result updateShare(@RequestBody
                              @Schema(description = "更新后的分享信息，需包含shareId", required = true) Share share) {
        Boolean success = shareService.updateShare(share);
        if (success) {
            return Result.success();
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/{shareId}")
    @Operation(summary = "删除分享", description = "删除指定ID的分享记录")
    @Parameters({
            @Parameter(name = "shareId", description = "分享ID", required = true, in = ParameterIn.PATH, example = "123")
    })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "删除成功"),
            @ApiResponse(responseCode = "404", description = "分享不存在"),
            @ApiResponse(responseCode = "403", description = "无权限删除")
    })
    public Result deleteShare(@PathVariable Integer shareId) {
        Boolean success = shareService.deleteShare(shareId);
        if (success) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除分享", description = "批量删除多个分享记录")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "批量删除成功"),
            @ApiResponse(responseCode = "400", description = "参数错误"),
            @ApiResponse(responseCode = "403", description = "无权限删除部分或全部记录")
    })
    public Result batchDeleteShares(@RequestBody
                                    @Schema(description = "分享ID列表", required = true) List<Integer> shareIds) {
        Boolean success = shareService.batchDeleteShares(shareIds);
        if (success) {
            return Result.success();
        }
        return Result.error("批量删除失败");
    }
}