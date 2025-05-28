import com.example.net_disk.entity.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileResourceRepository extends JpaRepository<FileResource, Long> {
    FileResource findByIdAndDeletedFalse(Long id);  // 查找未删除的文件
    FileResource findByFileNameAndUserIdAndDeletedFalse(String fileName, Long userId);  // 根据文件名和用户ID查找未删除的文件
}
