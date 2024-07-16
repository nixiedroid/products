package com.nixiedroid.products;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageResTest {
    @Test
    public void whenResourceAsFile_thenReadSuccessful()
            throws IOException {
        File resource = new ClassPathResource(
                "static/chair.png").getFile();

    }
}
