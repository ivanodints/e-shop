package ru.geekbrains.controller.DTO;

import ru.geekbrains.persist.model.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryAdminDTO implements Serializable {

    @Id
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String title;

    private long productCount;

    public CategoryAdminDTO(String title, long productCount) {

        this.title = title;
        this.productCount = productCount;
    }

    public CategoryAdminDTO(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }
}
