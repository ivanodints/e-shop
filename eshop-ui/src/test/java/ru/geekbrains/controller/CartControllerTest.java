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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    private CartService cartService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void cartPageTest() throws Exception {

        mvc.perform(get("/cart"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("lineItems"));
    }



    @Test
    public void addToCartTest() throws Exception{

//        cartService = new CartServiceImpl();
//
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(1L);
//        productDTO.setTitle("V8");
//        productDTO.setPrice(new BigDecimal(2000));
//        productDTO.setManufacturer(new Manufacturer("BMW"));
//        productDTO.setCategory(new Category("Engine"));


        ProductDTO productDTO = mock(ProductDTO.class);
        cartService = mock(CartServiceImpl.class);

        mvc.perform(post("/cart")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("productId", String.valueOf(productDTO.getId()))
                .param("qty", "2")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/categories"));

//        Optional<Category> opt = categoryRepository.findOne(Example.of(new Category("Cat")));
//
//        assertTrue(opt.isPresent());
//        assertEquals("Cat", opt.get().getTitle());

    }

}
