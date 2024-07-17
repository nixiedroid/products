package com.nixiedroid.products.repository;

import com.nixiedroid.products.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {

    Optional<Product> findDistinctById(Long id);

    /*
    SELECT *
    FROM site156.product
    ORDER BY rating DESC
    LIMIT 1;
     */
    @Query("SELECT p FROM Product p ORDER BY p.rating DESC LIMIT 1")
    Optional<Product> findTopRatedProduct();

    /*
    SELECT *
FROM site156.product
ORDER BY price ASC
LIMIT 1;
     */

    @Query("SELECT p FROM Product p ORDER BY p.price DESC LIMIT 1")
    Optional<Product> findTopPriceProduct();

    @Query("SELECT p FROM Product p ORDER BY p.price ASC LIMIT 1")
    Optional<Product> findLowestPriceProduct();

}
