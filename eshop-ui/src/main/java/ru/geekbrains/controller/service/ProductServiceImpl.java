package ru.geekbrains.controller.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductService::mapToDTO);
    }


    @Override
    public List<ProductDTO> findByManufacturer (Long manufacturerId) {
        Specification<Product> spec = ProductSpecification.fetchPictures();
        if (manufacturerId != null) {
            spec = spec.and(ProductSpecification.equalByManufacturer(manufacturerId));
        }
        return productRepository.findAll(spec).stream()
                .map(ProductService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> findWithFilter(String productTitleFilter,
                                           Integer pageNumber,
                                           Integer tableSize,
                                           String sort) {

        Specification<Product> spec = Specification.where(null);


        if (productTitleFilter != null && !productTitleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(productTitleFilter));
        }
        if (sort == null) {
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductDTO::new);
        } else if (sort.isEmpty()){
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize))
                    .map(ProductDTO::new);
        } else {
            return productRepository.findAll(spec, PageRequest.of(pageNumber, tableSize, Sort.by(sort).ascending()))
                    .map(ProductDTO::new);
        }
    }


    @Override
    public List<ProductDTO> findByCategory(Long categoryId) {
        Specification<Product> spec = ProductSpecification.fetchPictures();
        if (categoryId != null) {
            spec = spec.and(ProductSpecification.equalByCategory(categoryId));
        }
        return productRepository.findAll(spec).stream()
                .map(ProductService::mapToDTO)
                .collect(Collectors.toList());
    }






}
