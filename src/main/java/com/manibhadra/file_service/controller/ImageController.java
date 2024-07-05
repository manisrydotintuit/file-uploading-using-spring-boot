package com.manibhadra.file_service.controller;

import com.manibhadra.file_service.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    StorageService storageService;

    @PostMapping("uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        boolean imageExists = storageService.existsByName(file.getOriginalFilename());
        if (imageExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Image already exists");
        }

        String uploadImage = storageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @GetMapping("downloadImage/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] imageData = storageService.downLoadImage(fileName);
        if (imageData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
