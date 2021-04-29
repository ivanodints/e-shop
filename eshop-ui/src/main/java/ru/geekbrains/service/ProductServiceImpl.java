package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;
import ru.geekbrains.persist.repo.specification.ProductSpecification;

import java.util.ArrayList;
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
    public Page<ProductDTO> findWithFilter(Long categoryId,
                                           Long manufacturerId,
                                           Integer pageNumber,
                                           Integer tableSize) {

        Specification<Product> spec = Specification.where(null);


        if (categoryId != null) {
            spec = spec.and(ProductSpecification.equalByCategory(categoryId));
        }

        if (manufacturerId != null) {
            spec = spec.and(ProductSpecification.equalByManufacturer(manufacturerId));
        }

        Page<Long> ids = productRepository.findAll(spec, PageRequest.of(pageNumber - 1, tableSize))
                .map(product1 -> product1.getId());

        List<ProductDTO> allByIds = new ArrayList<>();
        for (Product product : productRepository.findAllByIds(ids.getContent())) {
            ProductDTO productDTO =ProductService.mapToDTO(product);
            allByIds.add(productDTO);
        }
        return new PageImpl<>(allByIds, PageRequest.of(pageNumber - 1, tableSize), ids.getTotalElements());
    }


    @Override
    public Page<ProductDTO> findWithTitleFilter(String productTitleFilter,
                                                String sort,
                                                Integer pageNumber,
                                                Integer tableSize) {

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
