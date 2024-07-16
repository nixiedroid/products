package com.nixiedroid.products.repository;

import com.nixiedroid.products.models.Feature;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeatureRepository extends CrudRepository<Feature,Long> {

    Optional<Feature> findDistinctByName(String name);
}
