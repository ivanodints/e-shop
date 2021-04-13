package ru.geekbrains.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.ProductService;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ManufacturerRepository;
import ru.geekbrains.service.PictureService;

import java.util.Optional;

@Controller
@RequestMapping
public class ProductListController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final PictureService pictureService;
    private final ManufacturerRepository manufacturerRepository;

    public ProductListController(ProductService productService,
                                 CategoryRepository categoryRepository,
                                 PictureService pictureService,
                                 ManufacturerRepository manufacturerRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.pictureService = pictureService;
        this.manufacturerRepository = manufacturerRepository;
    }


    @GetMapping("/categories")
    public String categoriesPage(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                 @RequestParam("pageNumber") Optional<Integer> pageNumber,
                                 @RequestParam("tableSize") Optional<Integer> tableSize,
                                 @RequestParam("sort") Optional<String> sort,
                                 @RequestParam("productTitleFilter") Optional<String> productTitleFilter,
                                 Long pictureId,
                                 Model model) {

        Page<ProductDTO> productsDTO = productService.findWithFilter(
                productTitleFilter.orElse(null),
                pageNumber.orElse(1) - 1,
                tableSize.orElse(4),
                sort.orElse(null)
        );

        model.addAttribute("allProd", productsDTO );
        model.addAttribute("products", productService.findByCategory(categoryId));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("pict", pictureService.showAllPictures());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());

        return "shop-sidebar";
    }

    @GetMapping("/categories/manufacturer")
    public String manufacturersPage(@RequestParam(value = "manufacturerId", required = false) Long manufacturerId,
                                 Long pictureId,
                                 Model model) {

        model.addAttribute("products", productService.findByManufacturer(manufacturerId));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("pict", pictureService.showAllPictures());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());

        return "shop-sidebar";
    }




    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("product", productService.findById(id).orElseThrow(Exception::new));
        return "product-details-sticky-right";
    }



}


