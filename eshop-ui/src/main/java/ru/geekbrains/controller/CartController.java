package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.controller.DTO.CartItemDTO;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.CartService;
import ru.geekbrains.controller.service.ProductService;



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
        return "cart";
    }

    @PostMapping
    public  String addToCart(CartItemDTO cartItemDTO) throws Exception {
        ProductDTO productDTO= productService.findById(cartItemDTO.getProductId()).orElseThrow(Exception::new);
        cartService.addProductQty(productDTO, cartItemDTO.getQty());
        return "redirect:/cart";
    }


}