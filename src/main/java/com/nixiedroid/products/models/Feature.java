package com.nixiedroid.products.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "feature", schema = "site156")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, mappedBy = "special_features", fetch = FetchType.LAZY)
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Product> products = new HashSet<>();


    // Helper method to manage bi-directional relationship
    public void addProduct(Product product) {
        products.add(product);
        product.getSpecial_features().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getSpecial_features().remove(this);
    }
}
