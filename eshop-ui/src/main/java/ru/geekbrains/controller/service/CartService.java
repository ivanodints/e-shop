package ru.geekbrains.controller.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface CartService {

    void addProductQty(ProductDTO productDTO,int qty);

    void removeProductQty(ProductDTO productDTO,int qty);

    void removeProduct(ProductDTO productDTO);

    List<LineItem> getLineItems();


}
