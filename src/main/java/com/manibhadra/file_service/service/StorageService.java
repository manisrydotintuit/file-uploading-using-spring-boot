package com.manibhadra.file_service.service;

import com.manibhadra.file_service.model.ImageData;
import com.manibhadra.file_service.repo.StorageRepo;
import com.manibhadra.file_service.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepo storageRepo;

    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = storageRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())

                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "File uploaded successfully " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downLoadImage(String fileName) {
        Optional<ImageData> dbImageData = storageRepo.findByName(fileName);
        return dbImageData.map(imageData -> ImageUtils.deCompressImage(imageData.getImageData())).orElse(null);
    }

    public boolean existsByName(String name) {
        return storageRepo.existsByName(name);
    }
}
