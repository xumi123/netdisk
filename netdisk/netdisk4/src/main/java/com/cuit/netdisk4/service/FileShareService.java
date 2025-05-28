
import com.cuit.netdisk4.entity.FileShare;

public interface FileShareService {
    FileShare createShareLink(Long fileId, Long userId);
}


