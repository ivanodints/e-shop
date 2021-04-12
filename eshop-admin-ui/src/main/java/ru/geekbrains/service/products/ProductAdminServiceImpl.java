package ru.geekbrains.service.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controller.DTO.ProductAdminDTO;
import ru.geekbrains.errors.NotFoundException;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.specification.ProductSpecification;
import ru.geekbrains.service.PictureService;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductAdminServiceImpl implements ProductAdminService, Serializable {

    private final ProductRepository productRepository;
    private final PictureService pictureService;

    @Autowired
    public ProductAdminServiceImpl(ProductRepository productRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
    }

    @Override
    public List<ProductAdminDTO> showAllProducts() {
        return productRepository.findAll().stream().map(ProductAdminDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<ProductAdminDTO> findProductById(Long id) {
        return productRepository.findById(id).map(ProductAdminDTO::new);
    }


    @Override
    public void saveProduct(ProductAdminDTO productAdminDTO) throws IOException {

        Product product = (productAdminDTO.getId() != null) ? productRepository.findById(productAdminDTO.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        product.setId(productAdminDTO.getId());
        product.setTitle(productAdminDTO.getTitle());
        product.setPrice(productAdminDTO.getPrice());
        product.setManufacturer(productAdminDTO.getManufacturer());
        product.setCategory(productAdminDTO.getCategory());

        if (productAdminDTO.getNewPictures() != null) {
            for (MultipartFile newPicture : productAdminDTO.getNewPictures()) {
//                logger.info("Product {} file {} size {} contentType {}", productRepr.getId(),
//                        newPicture.getOriginalFilename(), newPicture.getSize(), newPicture.getContentType());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        product
                ));
            }
        }

        productRepository.save(product);

    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductAdminDTO> findWithFilter(String productTitleFilter,
                                                Integer minPriceFilter,
                                                Integer maxPriceFilter,
                                                Integer pageNumber,
                                                Integer tableSize,
                                                String sort) {

        Specification <Product> spec = Specification.where(null);

        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(productTitleFilter));
        }
        if (minPriceFilter != null) {
            spec = spec.and(ProductSpecification.minPrice(minPriceFilter));
        }
        if (maxPriceFilter != null) {
            spec = spec.and(ProductSpecification.maxPrice(maxPriceFilter));
        }
        if (sort == null) {
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize)).map(ProductAdminDTO::new);

        } else if (sort.isEmpty()){
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize)).map(ProductAdminDTO::new);
        } else {
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize, Sort.by(sort).ascending())).map(ProductAdminDTO::new);
        }
    }


}
