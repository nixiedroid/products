package com.nixiedroid.products.controller;

import com.nixiedroid.products.models.ProductDTO;
import com.nixiedroid.products.service.ErrorMapper;
import com.nixiedroid.products.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@WithMockUser(username = "admin", roles = "ADMIN")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ErrorMapper mapper;

    @MockBean
    private ProductService service;

    @Test
    void getProducts() throws Exception {
        Mockito.when(service.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getProductById() throws Exception {
        ProductDTO dto = new ProductDTO(
                2L, "test", null, 0,
                null, null, null, false, 0,
                null, null, null, null);

        Mockito.when(service.findById(2L)).thenReturn(Optional.of(dto));
        Mockito.when(service.findById(-2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("test")));

        mockMvc.perform(get("/api/products/-2"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnRecentTacos() {
//        Product p1 = new Product();
//        p1.setName("Product 1");
//        Product p2 = new Product();
//        p2.setName("Product 2");
//        Product p3 = new Product();
//        p3.setName("Product 12");
//        List<Product> ps = List.of(p1,p2,p3);
//        ProductRepository pRepo = Mockito.mock(ProductRepository.class);
//        FeatureRepository fRepo = Mockito.mock(FeatureRepository.class);
//        when(pRepo.findAll()).thenReturn(ps);
//        WebTestClient testClient = WebTestClient.bindToController(new ProductService(pRepo,fRepo)).build();
//        testClient.get().uri("/api/products")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$").isArray()
//                .jsonPath("$").isNotEmpty()
//                .jsonPath("$[0].name").isEqualTo("Product 1")
//                .jsonPath("$[1].name").isEqualTo("Product 2")
//                .jsonPath("$[2].name").isEqualTo("Product 12")
//                .jsonPath("$[12]").doesNotExist();
    }

    @Test
    void addProduct() {
        Mockito.when(service.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    void putProduct() {
        Mockito.when(service.existsById(2L)).thenReturn(true);
        Mockito.when(service.existsById(1L)).thenReturn(false);
//        mockMvc.perform(put("/api/products/1"))
//                .andExpect(status().isNotFound());
//        mockMvc.perform(put("/api/products/2"))
//                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void deleteProduct() throws Exception {
        Mockito.when(service.existsById(2L)).thenReturn(true);
        Mockito.when(service.existsById(1L)).thenReturn(false);
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/products/2"))
                .andExpect(status().isForbidden());
    }
}