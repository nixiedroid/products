package com.nixiedroid.products.controller;

import com.nixiedroid.products.service.RemoveBgService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = "/api/removeBg")
public class RemoveBgController {
    RemoveBgService service;

    @Autowired
    public RemoveBgController(RemoveBgService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<byte[]> downloadFile(@RequestParam("file") MultipartFile file) {
        byte[] responce;
        try {
            responce = service.removeBackgroundBytes(file.getBytes());
        } catch (IOException  |FeignException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

        return new ResponseEntity<>(responce, headers, HttpStatus.OK);
    }
}
