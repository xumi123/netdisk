import com.cuit.netdisk4.entity.FileShare;
import com.cuit.netdisk4.service.FileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
public class FileShareController {

    @Autowired
    private FileShareService fileShareService;

    @PostMapping("/create")
    public FileShare createShareLink(@RequestParam Long fileId, @RequestParam Long userId) {
        return fileShareService.createShareLink(fileId, userId);
    }
}

