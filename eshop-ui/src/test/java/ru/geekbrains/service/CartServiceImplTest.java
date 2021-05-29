package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceImplTest {

    private CartService cartService;

    @BeforeEach
    public void initCart(){
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {

        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());

    }

    @Test
    public void testAddProductInCart() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setCategory(new Category("Construct"));
        productDTO.setManufacturer(new Manufacturer("lego"));
        productDTO.setPrice(new BigDecimal(20));
        productDTO.setTitle("Fire-car");

        cartService.addProductQty(productDTO, 1);

        List<LineItem> lineItems = cartService.getLineItems();
        LineItem lineItem = lineItems.get(0);

        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());
        assertEquals(productDTO.getId(), lineItem.getProductId());
        assertEquals(productDTO.getPrice(), lineItem.getProductDTO().getPrice());
        assertEquals(productDTO.getCategory(), lineItem.getProductDTO().getCategory());
        assertEquals(productDTO.getManufacturer(), lineItem.getProductDTO().getManufacturer());
        assertEquals(productDTO.getTitle(), lineItem.getProductDTO().getTitle());
    }

    @Test
    public void testSubTotalInCart() {

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(1L);
        productDTO1.setCategory(new Category("Construct"));
        productDTO1.setManufacturer(new Manufacturer("lego"));
        productDTO1.setPrice(new BigDecimal(20));
        productDTO1.setTitle("Fire-car");

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);
        productDTO2.setCategory(new Category("Engine"));
        productDTO2.setManufacturer(new Manufacturer("BMW"));
        productDTO2.setPrice(new BigDecimal(2000));
        productDTO2.setTitle("V8");

        ProductDTO productDTO3 = new ProductDTO();
        productDTO3.setId(3L);
        productDTO3.setCategory(new Category("Construct"));
        productDTO3.setManufacturer(new Manufacturer("Blocks"));
        productDTO3.setPrice(new BigDecimal(12));
        productDTO3.setTitle("Police-car");

        cartService.addProductQty(productDTO1, 1);
        cartService.addProductQty(productDTO2, 1);
        cartService.addProductQty(productDTO3, 1);

        System.out.println(cartService.getSubTotal());

        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(3, lineItems.size());
        assertEquals(new BigDecimal(2032), cartService.getSubTotal());

    }

    @Test
    public void testAddQTYProductInCart() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setCategory(new Category("Construct"));
        productDTO.setManufacturer(new Manufacturer("lego"));
        productDTO.setPrice(new BigDecimal(20));
        productDTO.setTitle("Fire-car");

        cartService.addProductQty(productDTO, 4);

        List<LineItem> lineItems = cartService.getLineItems();
        LineItem lineItem = lineItems.get(0);

        assertNotNull(lineItems);
        assertEquals(4, lineItem.getQty());

    }

}
