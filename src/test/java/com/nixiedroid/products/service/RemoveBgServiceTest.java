package com.nixiedroid.products.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class RemoveBgServiceTest {

    RemoveBgService svc;

    @Autowired
    public RemoveBgServiceTest(RemoveBgService svc) {
        this.svc = svc;
    }

    @Test
    void removeBackgroundTest() throws IOException {
        svc.removeBackground(new File("resources/chair.png"));
    }
}