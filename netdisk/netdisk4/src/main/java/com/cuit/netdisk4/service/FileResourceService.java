
import com.example.net_disk.entity.FileResource;

public interface FileResourceService {
    FileResource uploadFile(Long userId, String fileName, String fileType, String filePath, Long fileSize);
    FileResource downloadFile(Long fileId);
    boolean deleteFile(Long fileId);
}
