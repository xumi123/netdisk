
import com.example.net_disk.entity.FileShare;

public interface FileShareService {
    FileShare createShareLink(Long fileId, Long userId);
}


