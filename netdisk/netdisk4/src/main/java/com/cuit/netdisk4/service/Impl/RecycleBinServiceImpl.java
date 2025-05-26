

import com.cuit.netdisk4.Repository.RecycleBinRepository;
import com.cuit.netdisk4.entity.RecycleBin;
import com.cuit.netdisk4.service.RecycleBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecycleBinServiceImpl implements RecycleBinService {

    @Autowired
    private RecycleBinRepository recycleBinRepository;

    @Override
    public boolean restoreFile(Long fileId) {
        recycleBinRepository.deleteByFileId(fileId);
        return true;
    }

    @Override
    public void clearRecycleBin() {
        recycleBinRepository.deleteAll();
    }

    @Override
    public List<RecycleBin> getRecycleBin(Long userId) {
        return recycleBinRepository.findByUserId(userId);
    }
}
