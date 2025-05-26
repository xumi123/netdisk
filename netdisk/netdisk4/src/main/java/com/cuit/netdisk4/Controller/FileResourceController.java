import com.cuit.netdisk4.entity.FileResource;
import com.cuit.netdisk4.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
public class FileResourceController {

    @Autowired
    private FileResourceService fileResourceService;

    @PostMapping("/upload")
    public FileResource uploadFile(@RequestParam Long userId, @RequestParam String fileName, @RequestParam String fileType,
                                   @RequestParam String filePath, @RequestParam Long fileSize) {
        return fileResourceService.uploadFile(userId, fileName, fileType, filePath, fileSize);
    }

    @GetMapping("/download/{fileId}")
    public FileResource downloadFile(@PathVariable Long fileId) {
        return fileResourceService.downloadFile(fileId);
    }

    @DeleteMapping("/delete/{fileId}")
    public boolean deleteFile(@PathVariable Long fileId) {
        return fileResourceService.deleteFile(fileId);
    }
}
