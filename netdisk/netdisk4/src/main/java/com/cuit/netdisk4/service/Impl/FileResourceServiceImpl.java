  


import com.cuit.netdisk4.Repository.FileResourceRepository;
import com.cuit.netdisk4.entity.FileResource;
import com.cuit.netdisk4.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FileResourceServiceImpl implements FileResourceService {

    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Override
    public FileResource uploadFile(Long userId, String fileName, String fileType, String filePath, Long fileSize) {
        FileResource fileResource = new FileResource();
        fileResource.setUserId(userId);
        fileResource.setFileName(fileName);
        fileResource.setFileType(fileType);
        fileResource.setFilePath(filePath);
        fileResource.setFileSize(fileSize);
        fileResource.setDeleted(false);
        fileResource.setCreateTime(LocalDateTime.now());
        fileResource.setUpdateTime(LocalDateTime.now());
        return fileResourceRepository.save(fileResource);
    }

    @Override
    public FileResource downloadFile(Long fileId) {
        Optional<FileResource> fileOpt = Optional.ofNullable(fileResourceRepository.findByIdAndDeletedFalse(fileId));
        return fileOpt.orElse(null);
    }

    @Override
    public boolean deleteFile(Long fileId) {
        Optional<FileResource> fileOpt = Optional.ofNullable(fileResourceRepository.findByIdAndDeletedFalse(fileId));
        if (fileOpt.isPresent()) {
            FileResource fileResource = fileOpt.get();
            fileResource.setDeleted(true);
            fileResource.setDeleteTime(LocalDateTime.now());
            fileResourceRepository.save(fileResource);
            return true;
        }
        return false;
    }
}

