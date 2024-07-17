package com.nixiedroid.products.models;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

class ProductDTOTest {
    @Test
    void equalsTest() {
        ProductDTO dto1 = new ProductDTO(
                1L, "a", "a",
                1, "a", "a",
                "a", false, 1, Collections.emptyList()
                , "2", "6 monts",
                Set.of(
                        new FeatureDTO("u"),
                        new FeatureDTO("g")
                ));
        ProductDTO dto2 = new ProductDTO(
                1L, "a", "a",
                1, "a", "a",
                "a", false, 1,
                Collections.emptyList(), "2", "6 monts",
                Set.of(
                        new FeatureDTO("u"),
                        new FeatureDTO("g")
                ));
        ProductDTO dto3 = new ProductDTO(
                1L, "b", "a",
                1, "a", "a",
                "a", false, 1,
                Collections.emptyList(), "2", "6 monts",
                Set.of(
                        new FeatureDTO("u"),
                        new FeatureDTO("g")
                ));

        Assertions.assertEquals(dto1,dto2);
        Assertions.assertNotEquals(dto1,dto3);
    }
}