package com.fayardev.regmsfileserver.controller;

import com.fayardev.regmsfileserver.UploadResponseMessage;
import com.fayardev.regmsfileserver.dtos.AvatarDto;
import com.fayardev.regmsfileserver.image.ImageMultipart;
import com.fayardev.regmsfileserver.image.abstracts.ImageCompression;
import com.fayardev.regmsfileserver.security.FileSecurity;
import com.fayardev.regmsfileserver.services.FileService;
import com.fayardev.regmsfileserver.util.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final FileService fileService;
    private final ImageCompression imageCompression;

    @Autowired
    public FilesController(FileService fileService, ImageCompression imageCompression) {
        this.fileService = fileService;
        this.imageCompression = imageCompression;
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<UploadResponseMessage> uploadProfileImage(@RequestBody AvatarDto avatarDto) {
        if (!FileSecurity.uploadAccess(avatarDto.getToken())) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UploadResponseMessage("Invalid State"));
        }

        String imgBase64 = avatarDto.getBase64();
        String filename = avatarDto.getName();

        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(imgBase64);
        try {
            imageCompression.compress(new ImageMultipart(file), filename);
            return ResponseEntity.status(HttpStatus.OK).body(new UploadResponseMessage("Uploaded the file successfully: " + filename));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UploadResponseMessage("Could not upload the file: " + file.getOriginalFilename() + "!"));
        }
    }

    @GetMapping("/images/user-avatar/{filename}")
    public ResponseEntity<Resource> getUserAvatar(@PathVariable String filename) {
        try {
            Resource file = fileService.load(filename, fileService.uploadUserAvatarPath);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}