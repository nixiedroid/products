package com.nixiedroid.products.controller;

import com.nixiedroid.products.service.RemoveBgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/removeBg")
public class RemoveBgController {
    RemoveBgService service;

    @Autowired
    public RemoveBgController(RemoveBgService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Get file details
            String fileName = file.getOriginalFilename();
            long fileSize = file.getSize();
            String fileContentType = file.getContentType();

            byte[] bytes = file.getBytes();
            service.removeBackground(bytes);

            return ResponseEntity.ok("File uploaded successfully: " +
                    "Name: " + fileName + ", " +
                    "Size: " + fileSize + " bytes, " +
                    "Type: " + fileContentType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload file: " + e.getMessage());
        }
    }
}
