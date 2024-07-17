package com.nixiedroid.products.service;

import com.nixiedroid.products.models.FeatureDTO;
import com.nixiedroid.products.models.ImageDTO;
import com.nixiedroid.products.models.Product;
import com.nixiedroid.products.models.ProductDTO;
import com.nixiedroid.products.repository.FeatureRepository;
import com.nixiedroid.products.repository.ImageRepository;
import com.nixiedroid.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final FeatureRepository featureRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, FeatureRepository featureRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.featureRepository = featureRepository;
        this.imageRepository = imageRepository;
    }


    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findDistinctById(id).map(this::convertToDTO);
    }

    public Optional<ProductDTO> findBestProduct() {
        return productRepository.findTopRatedProduct().map(this::convertToDTO);
    }

    public Optional<ProductDTO> findCheapestProduct() {
        return productRepository.findLowestPriceProduct().map(this::convertToDTO);
    }

    public Optional<ProductDTO> findExpensiveProduct() {
        return productRepository.findTopPriceProduct().map(this::convertToDTO);
    }

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO save(ProductDTO dto) {
        return convertToDTO(productRepository.save(convertToEntity(dto)));
    }


    private ProductDTO convertToDTO(Product product) {
        if (product == null) return null;
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getColor(),
                product.getBrand(),
                product.getCategory(),
                product.isAvailability(),
                product.getRating(),
                product.getProduct_images().stream().map(i -> new ImageDTO(
                        i.getId(), i.getImageUrl()
                )).collect(Collectors.toList()),
                product.getWeight(),
                product.getWarranty(),
                product.getSpecialFeatures().stream()
                        .map(f -> new FeatureDTO(f.getName())
                        )
                        .collect(Collectors.toSet())
        );
    }

    private Product convertToEntity(ProductDTO dto) {
        if (dto == null) throw new NullPointerException();
        //Check if we should insert or update product
        Optional<Product> productFromDB = Optional.empty();
        if (dto.id() != null) {
            productFromDB = productRepository.findDistinctById(dto.id());
        }
        Product p;
        if (productFromDB.isEmpty()) { //Insert sequence
            p = new Product();
            p.setName(dto.name());
            p.setDescription(getDescription(dto));
            p.setPrice(dto.price());
            p.setColor(dto.color());
            p.setBrand(dto.brand());
            p.setCategory(dto.category());
            p.setAvailability(dto.availability());
            p.setRating(dto.rating());
            p.addImageAll(
                    dto.image_url().stream()
                            .map(i -> imageRepository.findDistinctByImageUrlEqualsIgnoreCase(i.imageUrl()))
                            .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet()));
            p.setWeight(dto.weight());
            p.setWarranty(dto.warranty());
            p.addFeatureAll(
                    dto.special_features().stream()
                            .map(f -> featureRepository.findDistinctByName(f.name()))
                            .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet()));
            return p;
        } else { //Update Sequence
            p = productFromDB.get();
            p.setName(dto.name());
            p.setDescription(getDescription(dto));
            p.setPrice(dto.price());
            p.setColor(dto.color());
            p.setBrand(dto.brand());
            p.setCategory(dto.category());
            p.setAvailability(dto.availability());
            p.setRating(dto.rating());
            p.addImageAll(
                    dto.image_url().stream()
                            .map(i -> imageRepository.findDistinctByImageUrlEqualsIgnoreCase(i.imageUrl()))
                            .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet()));
            p.setWeight(dto.weight());
            p.setWarranty(dto.warranty());
            p.setSpecialFeatures(
                    dto.special_features()
                            .stream()
                            .map(f -> featureRepository.findDistinctByName(f.name()))
                            .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet()));
        }
        return productFromDB.get();
    }

    private String getDescription(ProductDTO p) {
        return p.name() + //Прекрасные наушники
                " " +
                p.brand() +//Acme
                ", цвет:" +
                p.color() +//черный
                ". " +
                p.special_features()
                        .stream().map(FeatureDTO::name)
                        .collect(Collectors.joining(",")) +
                ".";
    }
}
