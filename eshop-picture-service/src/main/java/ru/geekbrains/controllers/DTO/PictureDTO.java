package ru.geekbrains.controllers.DTO;

import ru.geekbrains.persist.model.Picture;

import java.io.Serializable;

public class PictureDTO implements Serializable {

    private Long id;

    private String title;

    private String contentType;

    public PictureDTO(Picture picture) {
        this.id = picture.getId();
        this.title = picture.getTitle();
        this.contentType = picture.getContentType();
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

