

import com.cuit.netdisk4.entity.RecycleBin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecycleBinRepository extends JpaRepository<RecycleBin, Long> {
    List<RecycleBin> findByUserId(Long userId);  // 获取用户的回收站文件
    void deleteByFileId(Long fileId);  // 根据文件ID删除回收站记录
}
