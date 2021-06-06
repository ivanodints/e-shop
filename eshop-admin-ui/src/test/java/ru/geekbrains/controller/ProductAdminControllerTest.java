package ru.geekbrains.controller;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import ru.geekbrains.EshopAdminUiApplication;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ManufacturerRepository;
import ru.geekbrains.persist.repo.PictureRepository;
import ru.geekbrains.persist.repo.ProductRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = EshopAdminUiApplication.class)

public class ProductAdminControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @BeforeEach
    public void initCategoryRepo() {
        categoryRepository.deleteAllInBatch();
    }

    @BeforeEach
    public void initManufacturerRepo() {
        manufacturerRepository.deleteAllInBatch();
    }

//    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
//    @Test
//    public void testCategoryCreation() throws Exception{
//        mvc.perform(post("/brand")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "-1")
//                .param("title", "Cat")
//                .with(csrf())
//        )
////                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/admin/categories"));
//
//        Optional<Category> opt = categoryRepository.findOne(Example.of(new Category("Cat")));
//
//        assertTrue(opt.isPresent());
//        assertEquals("Cat", opt.get().getTitle());
//
//    }

//    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
//    @Test
//    public void testManufacturerCreation() throws Exception{
//        mvc.perform(post("/manufacturers")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "-1")
//                .param("title", "AK")
//                .with(csrf())
//        )
////                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/admin/manufacturer/create"));
//
//        Optional<Manufacturer> opt = manufacturerRepository.findOne(Example.of(new Manufacturer("AK")));
//
//        assertTrue(opt.isPresent());
//        assertEquals("AK", opt.get().getTitle());
//
//    }


//    @Test
//    public void testProducCreate() throws Exception {
//
//        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("BMW"));
//        Category category = categoryRepository.save(new Category("Engines"));
//        Product product = productRepository.save(new Product("V8", new BigDecimal(2000),category, manufacturer));
//
//        mvc.perform(get("/product/create"))
////                .andExpect(status().is2xxSuccessful())
////                .andExpect(view().name("product_form"))
////                .andExpect(model().attributeExists("product"))
//                .andExpect(model().attributeExists("categories"))
//                .andExpect(model().attributeExists("manufacturers"))
//
////                .andExpect(model().attributeExists("create"))
//        ;
//
//    }

//    @Test
//    public void testShowProducts() throws Exception {
//
//        Manufacturer manufacturer = manufacturerRepository.save(new Manufacturer("BMW"));
//        Category category = categoryRepository.save(new Category("Engines"));
//        Product product = productRepository.save(new Product("V8", new BigDecimal(2000),category, manufacturer));
//
//        Manufacturer manufacturer2 = manufacturerRepository.save(new Manufacturer("Pirelli"));
//        Category category2 = categoryRepository.save(new Category("Tires"));
//        Product product2 = productRepository.save(new Product("Spikes", new BigDecimal(2000),category2, manufacturer2));
//
//        List<Product> productList=new ArrayList<>();
//        productList.add(product);
//        productList.add(product2);
//
//        productRepository.saveAll(productList);
//
//
//
//        mvc.perform(get("/products"))
////                .andExpect(status().is2xxSuccessful())
//                .andExpect(view().name("products"))
//                .andExpect(model().attributeExists("product"))
////                .andExpect(model().attributeExists("products"))
////                .andExpect(model().attributeExists("manufacturers"))
//
////                .andExpect(model().attributeExists("create"))
//        ;
//
//    }



}
