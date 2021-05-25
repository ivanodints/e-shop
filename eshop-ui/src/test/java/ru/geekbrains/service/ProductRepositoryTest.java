package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRepositoryTest {

    private ProductServiceImpl productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void initRepo() {

        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public  void  testFindById(){

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Engine");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setTitle("BMW");

        Product product = new Product();
        product.setId(1L);
        product.setCategory(category);
        product.setManufacturer(manufacturer);
        product.setPrice(new BigDecimal(2000));
        product.setTitle("V8");
        product.setPictures(new ArrayList<>());

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(product));

        Optional<ProductDTO> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(product.getId(), opt.get().getId());
        assertEquals(product.getPrice(), opt.get().getPrice());
        assertEquals(product.getCategory(), opt.get().getCategory());
        assertEquals(product.getManufacturer(), opt.get().getManufacturer());
        assertEquals(product.getTitle(), opt.get().getTitle());
    }


}
