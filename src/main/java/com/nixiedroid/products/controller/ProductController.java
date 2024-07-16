package com.nixiedroid.products.controller;

import com.nixiedroid.products.models.Product;
import com.nixiedroid.products.models.ProductDTO;
import com.nixiedroid.products.service.ErrorMapper;
import com.nixiedroid.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

/**
 * Controller class for  <a href="/api/products">/api/products</a> endpoint
 *
 * @see com.nixiedroid.products.models.Product
 * @see com.nixiedroid.products.models.ProductDTO
 */
@RestController
@RequestMapping(value = "/api/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;
    private final ErrorMapper mapper;

    @Autowired
    public ProductController(ProductService productService, ErrorMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    /**
     * Listens for GET requests at <a href="/api/products">/api/products</a>
     *
     * @return json list of {@link Product}
     */
    @GetMapping
    Iterable<ProductDTO> getProducts() {
        return productService.findAll();
    }

    /**
     * Listens for GET requests at <a href="/products{id}">/api/products/{id}</a>
     *
     * @return json object {@link Product} if {id} exists or null
     */
    @GetMapping("/{id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> dtoOptional = productService.findById(id);
        return dtoOptional.map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Listens for POST requests at <a href="/api/products">/api/products</a>
     * and creates product object accordingly
     *
     * @return newly created json object {@link Product} on success
     */
    @PostMapping
    ResponseEntity<?> addProduct(@RequestBody @Valid ProductDTO product, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(mapper.apply(errors), HttpStatus.BAD_REQUEST);
        }
        if (productService.existsById(product.id())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }


    /**
     * Listens for PUT requests at <a href="/products{id}">/api/products/{id}</a>
     * and updates product object if {id} found
     * or creates product object if not-exists
     *
     * @return newly created json object {@link Product} on success
     */
    @PutMapping("/{id}")
    ResponseEntity<?> putProduct(@PathVariable Long id,
                                          @Valid @RequestBody ProductDTO product, Errors errors
    ) {
        if (!Objects.equals(product.id(), id)) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (errors.hasErrors()) {
            return new ResponseEntity<>(mapper.apply(errors), HttpStatus.BAD_REQUEST);
        }
        if (productService.existsById(id)) {
            return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
        } else return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    /**
     * Listens for DELETE requests at <a href="/products/{id}">/api/products/{id}</a>
     * and deletes product object if {id} found
     */

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.existsById(id)) {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
