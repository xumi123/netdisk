

import com.example.net_disk.entity.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileShareRepository extends JpaRepository<FileShare, Long> {
    FileShare findByShareCode(String shareCode);  // 根据分享码查找
    FileShare findByFileIdAndUserId(Long fileId, Long userId);  // 根据文件ID和用户ID查找
}
