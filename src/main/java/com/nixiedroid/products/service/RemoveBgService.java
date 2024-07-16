package com.nixiedroid.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@SuppressWarnings("ResultOfMethodCallIgnored")
@Service
public class RemoveBgService {

    private final RemoveBgClient removeBgClient;
    @Value("${remove.bg.token}")
    public String TOKEN;
    @Value("${remove.bg.url}")
    public String url;

    @Value("${images.path.base}")
    public String rawPath;
    @Value("${images.path.raw}")
    public String basePath;
    @Value("${images.path.no-background}")
    public String processedPath;


    @Autowired
    public RemoveBgService(RemoveBgClient removeBgClient) {
        this.removeBgClient = removeBgClient;
    }

    public void removeBackground(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        File out = new File(rawPath + "/" + processedPath + "/" + file.getName());
        System.out.println(file.getAbsoluteFile());
        System.out.println(out.getAbsoluteFile());
        if (!out.exists()) out.createNewFile();
        byte[] outBytes = removeBackgroundBytes(bytes);
        try (FileOutputStream fos = new FileOutputStream(out)) {
            fos.write(outBytes);
        }
    }

    public void removeBackground(byte[] bytes) throws IOException {
        File out = new File(rawPath + "/" + processedPath + "/" + new Random().nextInt());
        if (!out.exists()) out.createNewFile();
        byte[] outBytes = removeBackgroundBytes(bytes);
        try (FileOutputStream fos = new FileOutputStream(out)) {
            fos.write(outBytes);
        }
    }

    public byte[] removeBackgroundBytes(byte[] file) {
        return removeBgClient.removeBackground(file, "preview", TOKEN);
    }

}
