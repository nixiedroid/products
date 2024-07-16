package com.nixiedroid.products.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "remove.bg")
public record RemoveBgAPI(
        String url,
        String token
) {
}
