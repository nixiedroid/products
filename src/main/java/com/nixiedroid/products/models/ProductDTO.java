package com.nixiedroid.products.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Set;

public record ProductDTO(

        Long id,
        @NotBlank(message = "Product name is required")
        String name,

        String description,
        @Positive
        double price,
        @NotBlank(message = "Product color is required")
        String color,
        @NotBlank(message = "Product brand is required")
        String brand,
        @NotBlank(message = "Product category is required")
        String category,

        boolean availability,
        @Min(value = 0, message = "Rating must be greater than 0")
        @Max(value = 5, message = "Rating must be lower than 5")
        double rating,

        List<ImageDTO> image_url,
        @NotBlank(message = "Product weight is required")
        String weight,
        @NotBlank(message = "Product warranty is required")
        String warranty,

        Set<FeatureDTO> special_features
) {
}
