package ru.geekbrains.controller.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface ProductService {



    Page<ProductDTO> findWithFilter(String productTitleFilter,
                                    Integer pageNumber,
                                    Integer tableSize,
                                    String sort
                                    );


    Optional <ProductDTO> findById(Long id);

    List<ProductDTO> findByCategory(Long categoryId);

    List<ProductDTO> findByManufacturer(Long manufacturerId);

    static ProductDTO mapToDTO(Product p) {
        return new ProductDTO(
                p.getId(),p.getTitle(), p.getPrice(), p.getManufacturer(),p.getCategory(),
                p.getPictures().size() > 0 ? p.getPictures().get(0).getId() : null,
                p.getPictures().stream().map(Picture::getId).collect(Collectors.toList()));
    }

}
