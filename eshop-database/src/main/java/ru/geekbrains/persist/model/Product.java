package ru.geekbrains.persist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "price",length = 16, nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    private Manufacturer manufacturer;

    @ManyToOne(optional = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Picture> pictures;

    public Product() {
    }

    public Product(String title, BigDecimal price, Category category, Manufacturer manufacturer) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title=" + title + '\'' +
                ", manufacturer=" + manufacturer + '\'' +
                ", category=" + category + '\'' +
                ", price=" + price +
                '}';
    }
}