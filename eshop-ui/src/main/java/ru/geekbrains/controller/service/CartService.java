package ru.geekbrains.controller.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.model.LineItem;

import java.util.List;

@Service
public interface CartService {

    void addProductQty(ProductDTO productDTO,int qty);

    void removeProductQty(ProductDTO productDTO,int qty);

    List<LineItem> getLineItems();
}
