package com.nixiedroid.products.repository;

import com.nixiedroid.products.models.ProductImage;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ImageRepository extends ListCrudRepository<ProductImage,Long> {
    Optional<ProductImage> findDistinctByImageUrlEqualsIgnoreCase(String url);
}
