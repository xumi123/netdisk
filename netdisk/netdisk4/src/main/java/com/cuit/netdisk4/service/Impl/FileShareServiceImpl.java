

import com.example.net_disk.Repository.FileShareRepository;
import com.example.net_disk.entity.FileShare;
import com.example.net_disk.service.FileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FileShareServiceImpl implements FileShareService {

    @Autowired
    private FileShareRepository fileShareRepository;

    @Override
    public FileShare createShareLink(Long fileId, Long userId) {
        FileShare fileShare = new FileShare();
        fileShare.setFileId(fileId);
        fileShare.setUserId(userId);
        fileShare.setShareCode(generateShareCode());
        fileShare.setExpireTime(LocalDateTime.now().plusDays(7)); // 分享链接有效期 7 天
        fileShare.setVisitCount(0);
        return fileShareRepository.save(fileShare);
    }

    private String generateShareCode() {
        // 简单的分享码生成逻辑
        return String.valueOf(System.currentTimeMillis() % 1000000000);
    }
}

