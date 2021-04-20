package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.DTO.CartItemDTO;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.CartService;
import ru.geekbrains.controller.service.ProductService;
import ru.geekbrains.controller.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/cart")
public class CartController {

    public final CartService cartService;
    public final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }


    @GetMapping
    public String cartPage(Model model) {
        model.addAttribute("lineItems", cartService.getLineItems());


        List<BigDecimal> collect = cartService.getLineItems().stream().map(LineItem::getTotal).collect(Collectors.toList());

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : collect) {
            totalPrice = totalPrice.add(bigDecimal);

        model.addAttribute("totalPrice", totalPrice);
        }
        return "cart";
    }

    @PostMapping
    public  String addToCart(CartItemDTO cartItemDTO) throws Exception {
        ProductDTO productDTO= productService.findById(cartItemDTO.getProductId()).orElseThrow(Exception::new);
        cartService.addProductQty(productDTO, cartItemDTO.getQty());
        return "redirect:/categories";
    }


    @DeleteMapping("/product/{id}/delete/{qty}")
    public String removeFromCart(@RequestParam(value = "id") int productId ,
                                 @RequestParam(value = "qty") int qty) throws Exception {

        ProductDTO productDTO = productService.findById((long) productId)
                .orElseThrow(Exception::new);

        cartService.removeProductQty(productDTO, qty);

        return "redirect:/cart";
    }


}