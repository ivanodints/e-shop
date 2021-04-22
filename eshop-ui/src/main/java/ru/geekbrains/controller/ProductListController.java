package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.service.CartService;
import ru.geekbrains.controller.service.ProductService;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ManufacturerRepository;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.service.PictureService;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping
public class ProductListController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final PictureService pictureService;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;


    public ProductListController(ProductService productService,
                                 CategoryRepository categoryRepository,
                                 PictureService pictureService,
                                 ManufacturerRepository manufacturerRepository,
                                 ProductRepository productRepository, CartService cartService) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.pictureService = pictureService;
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }


    @GetMapping("/categories")
    public String categoriesPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                 @RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                 @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                 @RequestParam(value = "tableSize", required = false, defaultValue = "6") Integer tableSize,
                                 @RequestParam(value = "sort", required = false) Optional<String> sort,
                                 Long pictureId,
                                 Model model) {

        model.addAttribute("allProd", productRepository.findAll());
        model.addAttribute("allCat", categoryRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("pict", pictureService.showAllPictures());
        model.addAttribute("products", productService.findWithFilter(categoryId,manufacturerId, pageNumber, tableSize));
        return "shop-sidebar";
    }

    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("product", productService.findById(id).orElseThrow(Exception::new));
        return "product-details-sticky-right";
    }




}


