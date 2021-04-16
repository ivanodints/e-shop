package ru.geekbrains.controller.DTO;

import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Manufacturer;
import ru.geekbrains.persist.model.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotEmpty
    private final String title;

    private final BigDecimal price;

    private  Manufacturer manufacturer;

    private  Category category;

    private  Long pictureId;

    private  List<Long> pictureIds;


    public ProductDTO(@NotEmpty Long id, @NotEmpty String title, BigDecimal price, Manufacturer manufacturer, Category category, Long pictureId, List<Long> pictureIds) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
        this.pictureId = pictureId;
        this.pictureIds = pictureIds;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.category = product.getCategory();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.title = product.getTitle();
    }

//    public ProductDTO(@NotEmpty Long id, @NotEmpty String title, BigDecimal price, Long pictureId, List<Long> pictureIds) {
//        this.id = id;
//        this.title = title;
//        this.price = price;
//        this.pictureId = pictureId;
//        this.pictureIds = pictureIds;
//    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public List<Long> getPictureIds() {
        return pictureIds;
    }

}
