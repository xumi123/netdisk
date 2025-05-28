package com.cuit.netdisk4.Share;

import com.cuit.netdisk4.Entity.Share;
import com.cuit.netdisk4.service.ShareService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShareServiceTest {

    @Autowired
    private ShareService shareService;

    @Test
    public void testCreateShare() {
        Share share = new Share();
        share.setUserId(1);
        share.setFileId(1);
        share.setShareType(0);
        share.setAccessPermission(0);
        share.setExpireTime(LocalDateTime.now().plusDays(1));

        Integer shareId = shareService.createShare(share);
        assertNotNull(shareId);
    }

    @Test
    public void testGetShareById() {
        Share share = shareService.getShareById(1);
        if (share != null) {
            assertEquals(1, share.getShareId());
        }
    }

    @Test
    public void testGetSharesByFileId() {
        List<Share> shares = shareService.getSharesByFileId(1);
        assertNotNull(shares);
    }

    @Test
    public void testGetSharesByUserId() {
        List<Share> shares = shareService.getSharesByUserId(1);
        assertNotNull(shares);
    }

    @Test
    public void testGetSharesByReceiverId() {
        List<Share> shares = shareService.getSharesByReceiverId(1);
        assertNotNull(shares);
    }

    @Test
    public void testValidateShareLink() {
        Map<String, Object> result = shareService.validateShareLink(1, null);
        assertNotNull(result);
    }

    @Test
    public void testUpdateShare() {
        Share share = shareService.getShareById(1);
        if (share != null) {
            share.setAccessPermission(1);
            boolean success = shareService.updateShare(share);
            assertTrue(success);
        }
    }

    @Test
    public void testDeleteShare() {
        boolean success = shareService.deleteShare(1);
        assertTrue(success);
    }

    @Test
    public void testBatchDeleteShares() {
        boolean success = shareService.batchDeleteShares(List.of(1, 2, 3));
        assertTrue(success);
    }

    @Test
    public void testCleanExpiredShares() {
        Integer count = shareService.cleanExpiredShares();
        assertNotNull(count);
    }
}