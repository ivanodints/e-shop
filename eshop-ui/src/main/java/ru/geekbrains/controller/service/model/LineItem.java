package ru.geekbrains.controller.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.geekbrains.controller.DTO.ProductDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LineItem implements Serializable {

    private Long productId;

    private ProductDTO productDTO;

    private Integer qty;


    public LineItem(ProductDTO productDTO) {
        this.productId = productDTO.getId();
        this.productDTO = productDTO;
    }

    public LineItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


    @JsonIgnore
    public BigDecimal getTotal() {
        return productDTO.getPrice().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return productId.equals(lineItem.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}