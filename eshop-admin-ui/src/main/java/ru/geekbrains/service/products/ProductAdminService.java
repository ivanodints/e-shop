package ru.geekbrains.service.products;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.DTO.ProductAdminDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductAdminService {

    List<ProductAdminDTO> showAllProducts();

    Optional<ProductAdminDTO> findProductById (Long id);

    void saveProduct (ProductAdminDTO productAdminDTO) throws IOException;

    void deleteProductById (Long id);

    Page<ProductAdminDTO> findWithFilter(String productTitleFilter,
                                         Integer minPriceFiler,
                                         Integer maxPriceFilter,
                                         Integer pageNumber,
                                         Integer tableSize,
                                         String sort);

}
