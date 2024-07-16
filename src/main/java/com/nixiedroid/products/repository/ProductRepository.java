package com.nixiedroid.products.repository;

import com.nixiedroid.products.models.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends ListCrudRepository<Product,Long> {

    Optional<Product> findDistinctById(Long id);
}
