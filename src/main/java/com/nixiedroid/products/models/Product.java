package com.nixiedroid.products.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode
@Table(name = "product", schema = "site156")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @ColumnDefault("0")
    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @ColumnDefault("false")
    @Column(name = "availability", nullable = false)
    private boolean availability;

    @ColumnDefault("5.0")
    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "warranty", nullable = false)
    private String warranty;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
    },fetch = FetchType.LAZY)
    @JoinTable(
            schema = "site156",
            name = "feature_map",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "feature_id",
                    referencedColumnName = "id"))
    @Getter(AccessLevel.PACKAGE)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
            @ToString.Exclude
    private Set<Feature> special_features = new HashSet<>();


    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "productId",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ProductImage> product_images = new ArrayList<>();


    public void addFeature(Feature feature) {
        special_features.add(feature);
        feature.getProducts().add(this);
    }

    public void addImageAll(Collection<? extends ProductImage> fs) {
        this.product_images.addAll(fs);
        fs.forEach(p-> p.setProductId(this));
    }

    public void addFeatureAll(Collection<? extends Feature> fs) {
        this.special_features.addAll(fs);
        fs.forEach(p-> p.addProduct(this));
    }

    public void setSpecialFeatures(Set<Feature> fs){
        this.special_features.clear();
        addFeatureAll(fs);
    }

    public Set<Feature> getSpecialFeatures(){
        return Collections.unmodifiableSet(special_features);
    }

    public void removeFeature(Feature feature) {
        special_features.remove(feature);
        feature.getProducts().remove(this);
    }
}