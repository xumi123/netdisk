

import com.example.net_disk.entity.RecycleBin;

import java.util.List;

public interface RecycleBinService {
    boolean restoreFile(Long fileId);
    void clearRecycleBin();
    List<RecycleBin> getRecycleBin(Long userId);
}


