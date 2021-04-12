package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.DTO.ProductAdminDTO;
import ru.geekbrains.errors.NotFoundException;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.repo.CategoryRepository;
import ru.geekbrains.persist.repo.ManufacturerRepository;
import ru.geekbrains.service.products.ProductAdminService;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

    private final ProductAdminService productAdminService;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ProductAdminController(ProductAdminService productAdminService, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productAdminService = productAdminService;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

//    @Secured({"ADMIN"})
//    @GetMapping("/products")
//    public String usersPage(Model model,
//                            @RequestParam("productTitleFilter") Optional<String> productTitleFilter,
//                            @RequestParam("minPriceFilter") Optional<Integer> minPriceFilter,
//                            @RequestParam("maxPriceFilter") Optional<Integer> maxPriceFilter,
//                            @RequestParam("pageNumber") Optional<Integer> pageNumber,
//                            @RequestParam("tableSize") Optional<Integer> tableSize,
//                            @RequestParam("sort") Optional<String> sortBy) {
//
//        Page<ProductDTO> products = productService.findWithFilter(
//                productTitleFilter.orElse(null),
//                minPriceFilter.orElse(null),
//                maxPriceFilter.orElse(null),
//                pageNumber.orElse(1) - 1,
//                tableSize.orElse(5),
//                sortBy.orElse(null)
//        );
//
//        model.addAttribute("products", products);
//        return "products";
//    }

    @Secured({"ADMIN"})
    @GetMapping("/products")
    public String adminProductsPage(Model model) {
        model.addAttribute("activePage", "Products");
        model.addAttribute("products", productAdminService.showAllProducts());
        return "products";
    }

    @Secured({"ADMIN"})
    @GetMapping("/product/{id}/edit")
    public String adminEditProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", productAdminService.findProductById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "product_form";
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/product/{id}/delete")
    public String adminDeleteProduct(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Products");
        productAdminService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @Secured({"ADMIN"})
    @GetMapping("/product/create")
    public String adminCreateProduct(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Products");
        model.addAttribute("product", new ProductAdminDTO());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "product_form";
    }

    @Secured({"ADMIN"})
    @PostMapping("/product")
    public String adminUpsertProduct(Model model, RedirectAttributes redirectAttributes, ProductAdminDTO product) {
        model.addAttribute("activePage", "Products");

        try {
            productAdminService.saveProduct(product);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", true);
            if (product.getId() == null) {
                return "redirect:/admin/product/create";
            }
            return "redirect:/admin/product/" + product.getId() + "/edit";
        }
        return "redirect:/admin/products";
    }

// Categories
    @Secured({"ADMIN"})
    @GetMapping("/categories")
    public String adminCategoriesPage(Model model) {
        model.addAttribute("activePage", "Categories");
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }
    @Secured({"ADMIN"})
    @GetMapping("/category/create")
    public String adminCategoryCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", new Category());
        return "category_form";
    }
    @Secured({"ADMIN"})
    @GetMapping("/category/{id}/edit")
    public String adminEditCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Categories");
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "category_form";
    }
    @Secured({"ADMIN"})
    @DeleteMapping("/category/{id}/delete")
    public String adminDeleteCategory(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Categories");
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
    @Secured({"ADMIN"})
    @PostMapping("/category")
    public String adminUpsertCategory(Model model, RedirectAttributes redirectAttributes, Category category) {
        model.addAttribute("activePage", "Categories");

        try {
            categoryRepository.save(category);
        } catch (Exception ex) {

            redirectAttributes.addFlashAttribute("error", true);
            if (category.getId() == null) {
                return "redirect:/admin/category/create";
            }
            return "redirect:/admin/category/" + category.getId() + "/edit";
        }
        return "redirect:/admin/categories";
    }

// Manufacturer
    @Secured({"ADMIN"})
    @GetMapping("/manufacturers")
    public String adminManufacturerPage(Model model) {
        model.addAttribute("activePage", "Manufacturers");
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "manufacturers";
    }
    @Secured({"ADMIN"})
    @GetMapping("/manufacturer/create")
    public String adminManufacturerCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Manufacturers");
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturer_form";
    }
    @Secured({"ADMIN"})
    @GetMapping("/manufacturer/{id}/edit")
    public String adminEditManufacturer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Manufacturers");
        model.addAttribute("manufacturer", manufacturerRepository.findById(id).orElseThrow(NotFoundException::new));
        return "manufacturer_form";
    }
    @Secured({"ADMIN"})
    @DeleteMapping("/manufacturer/{id}/delete")
    public String adminDeleteManufacturer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Manufacturers");
        manufacturerRepository.deleteById(id);
        return "redirect:/admin/manufacturers";
    }
    @Secured({"ADMIN"})
    @PostMapping("/manufacturer")
    public String adminUpsertManufacturer(Model model, RedirectAttributes redirectAttributes, Manufacturer manufacturer) {
        model.addAttribute("activePage", "Manufacturers");

        try {
            manufacturerRepository.save(manufacturer);
        } catch (Exception ex) {

            redirectAttributes.addFlashAttribute("error", true);
            if (manufacturer.getId() == null) {
                return "redirect:/admin/manufacturer/create";
            }
            return "redirect:/admin/manufacturer/" + manufacturer.getId() + "/edit";
        }
        return "redirect:/admin/manufacturers";
    }

}
