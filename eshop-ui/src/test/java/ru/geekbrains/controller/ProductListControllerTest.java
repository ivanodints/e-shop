package ru.geekbrains.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.EshopAdminUiApplication;
import ru.geekbrains.EshopUiApplication;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ManufacturerRepository;
import ru.geekbrains.persist.repo.ProductRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = EshopUiApplication.class)
public class ProductListControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void cleanCategories() {
        categoryRepository.deleteAllInBatch();
    }

    @BeforeEach
    public void cleanManufacturers() {
        manufacturerRepository.deleteAllInBatch();
    }

    @BeforeEach
    public void cleanProducts() {
        productRepository.deleteAllInBatch();
    }

    @Test
    public void testProductDetails() throws Exception {

        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("BMW"));
        Category category = categoryRepository.save(new Category("Engines"));
        Product product = productRepository.save(new Product("V8", new BigDecimal(2000),category, manufacturer));

        mvc.perform(get("/product/" + product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("product-details-sticky-right"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attribute("product", new BaseMatcher<Product>(){

                    @Override
                    public void describeTo(Description description) {
                    }
                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof ProductDTO) {
                            ProductDTO productDTO = (ProductDTO) o;
                            return productDTO.getTitle().equals(product.getTitle());
                        }
                        return false;
                    }
                }));
    }

    @Test
    public void testCategoriesPage() throws Exception {

        Manufacturer manufacturer1 = manufacturerRepository.save(new Manufacturer("BMW"));
        Category category1 = categoryRepository.save(new Category("Engines"));
        Product product1 = new Product("V8", new BigDecimal(2000),category1, manufacturer1);

        Manufacturer manufacturer2 = manufacturerRepository.save(new Manufacturer("Michelin"));
        Category category2 = categoryRepository.save(new Category("Tires"));
        Product product2 = new Product("Spike", new BigDecimal(200),category2, manufacturer2);

        Manufacturer manufacturer3 = manufacturerRepository.save(new Manufacturer("AUDI"));
        Product product3 = new Product("Turbo-V8", new BigDecimal(2500),category1, manufacturer3);

        List<Product> productList=new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        productRepository.saveAll(productList);

        mvc.perform(get("/categories"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("shop-sidebar"))
                .andExpect(model().attributeExists("allProd"))
                .andExpect(model().attributeExists("allCat"))
                .andExpect(model().attributeExists("manufacturers"))
                .andExpect(model().attributeExists("pict"))
                .andExpect(model().attributeExists("products"))
                .andExpect(model().attributeExists("products"))
        ;
    }
}
