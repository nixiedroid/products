package com.nixiedroid.products.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("unused")
@ConfigurationProperties(prefix = "images.path")
public record ImagesStorage(
        String base,
        String noBackground,
        String raw
) {
}
