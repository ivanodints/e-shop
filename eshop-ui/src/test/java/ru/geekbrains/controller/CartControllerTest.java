package ru.geekbrains.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
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
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.ProductService;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = EshopUiApplication.class)
public class CartControllerTest {

    @MockBean
    private CartService cartService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Product product1;
    private Product product2;


    @BeforeEach
    public void createTestProduct(){
        Category category1 = new Category("pants");
        categoryRepository.save(category1);
        Category category2 = new Category("boots");
        categoryRepository.save(category2);

        Manufacturer manufacturer1 = new Manufacturer("Nike");
        manufacturerRepository.save(manufacturer1);
        Manufacturer manufacturer2 = new Manufacturer("Puma");
        manufacturerRepository.save(manufacturer2);

        product1 = new Product("long-pants", new BigDecimal(100), category1, manufacturer1);
        productRepository.save(product1);

        product2 = new Product("slippers", new BigDecimal(50), category2, manufacturer2);
        productRepository.save(product2);

    }


    @Test
    public void cartPageTest() throws Exception {

        mvc.perform(get("/cart"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("lineItems"));
    }


    @Test
    public void testAddToCart() throws Exception{
        mvc.perform(post("/cart")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId",Long.toString(product1.getId()))
                .param("qty", "2")
                )
//                .andExpect(status().is3xxSuccessful())
                .andExpect(view().name("redirect:/categories"));

//        assertTrue(cartService.getLineItems().isEmpty());
        
    }
}
