package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CartService {

    void addProductQty(ProductDTO productDTO,int qty);

    void removeProductQty(ProductDTO productDTO,int qty);

    void removeProduct(ProductDTO productDTO);

    List<LineItem> getLineItems();

    BigDecimal getSubTotal();


}
