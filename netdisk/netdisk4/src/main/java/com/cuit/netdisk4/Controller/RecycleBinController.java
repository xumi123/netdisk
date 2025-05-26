import com.example.net_disk.entity.RecycleBin;
import com.example.net_disk.service.RecycleBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recyclebin")
public class RecycleBinController {

    @Autowired
    private RecycleBinService recycleBinService;

    @GetMapping("/{userId}")
    public List<RecycleBin> getRecycleBin(@PathVariable Long userId) {
        return recycleBinService.getRecycleBin(userId);
    }

    @PostMapping("/restore/{fileId}")
    public boolean restoreFile(@PathVariable Long fileId) {
        return recycleBinService.restoreFile(fileId);
    }

    @DeleteMapping("/clear")
    public void clearRecycleBin() {
        recycleBinService.clearRecycleBin();
    }
}
