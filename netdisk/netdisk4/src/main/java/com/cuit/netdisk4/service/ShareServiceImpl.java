package com.cuit.netdisk4.service;

import com.cuit.netdisk4.Dao.ShareDao;
import com.cuit.netdisk4.Entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ShareServiceImpl implements ShareService{
    @Autowired
    private ShareDao shareDao;
    @Override
    @Transactional
    public Integer createShare(Share share) {
        // 设置分享时间
        share.setShareTime(new Date());
        // 插入分享记录
        int result = shareDao.insert(share);
        if (result > 0) {
            return share.getShareId();
        }
        return null;
    }

    @Override
    public Share getShareById(Integer shareId) {
        return shareDao.selectById(shareId);
    }

    @Override
    public List<Share> getSharesByFileId(Integer fileId) {
        return shareDao.selectByFileId(fileId);
    }

    @Override
    public List<Share> getSharesByUserId(Integer userId) {
        return shareDao.selectByUserId(userId);
    }

    @Override
    public List<Share> getSharesByReceiverId(Integer receiverId) {
        return shareDao.selectByReceiverId(receiverId);
    }

    @Override
    public Map<String, Object> validateShareLink(Integer shareId, String password) {
        Map<String, Object> result = new HashMap<>();
        Share share = shareDao.selectById(shareId);

        if (share == null) {
            result.put("valid", false);
            result.put("message", "分享链接不存在");
            return result;
        }

        // 检查是否过期
        if (share.getExpireTime() != null && share.getExpireTime().isBefore(LocalDateTime.now())) {
            result.put("valid", false);
            result.put("message", "分享已过期");
            return result;
        }

        // 检查密码
        if (share.getShareType() == 2 && !Objects.equals(share.getSharePassword(), password)) {
            result.put("valid", false);
            result.put("message", "密码错误");
            return result;
        }

        // 检查接收者（如果是指定用户分享）
        if (share.getShareType() == 1 && share.getReceiverId() != null) {
            result.put("isReceiver", true);
        }

        result.put("valid", true);
        result.put("share", share);
        return result;
    }

    @Override
    public Boolean updateShare(Share share) {
        return shareDao.updateById(share) > 0;
    }

    @Override
    public Boolean deleteShare(Integer shareId) {
        return shareDao.deleteById(shareId) > 0;
    }

    @Override
    public Boolean batchDeleteShares(List<Integer> shareIds) {
        return shareDao.deleteBatchIds(shareIds) > 0;
    }

    @Override
    public Integer cleanExpiredShares() {
        return shareDao.deleteExpiredShares();
    }
}
