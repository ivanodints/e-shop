package ru.geekbrains.controller.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.ProductDTO;
import ru.geekbrains.controller.service.model.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {

    private final Map<LineItem, Integer> lineItems = new ConcurrentHashMap<>();

    @Override
    public void addProductQty(ProductDTO productDTO, int qty) {
        LineItem lineItem = new LineItem(productDTO);
        lineItems.put(lineItem, lineItems.getOrDefault(lineItem, 0) + qty);
    }

    @Override
    public void removeProductQty(ProductDTO productDTO, int qty) {
        LineItem lineItem = new LineItem(productDTO);
        int currentQty = lineItems.getOrDefault(lineItem, 0);
        if (currentQty - qty > 0) {
            lineItems.put(lineItem, currentQty - qty);
        } else {
            lineItems.remove(lineItem);
        }
    }

    @Override
    public void removeProduct(ProductDTO productDTO) {
        LineItem lineItem = new LineItem(productDTO);
        int currentQty = lineItems.getOrDefault(lineItem, 0);
            lineItems.remove(lineItem);
        }


    @Override
    public List<LineItem> getLineItems() {
        for (Map.Entry<LineItem, Integer> entry : lineItems.entrySet()) {
            LineItem key = entry.getKey();
            Integer value = entry.getValue();
            key.setQty(value);
        }
        return new ArrayList<>(lineItems.keySet());
    }



}



