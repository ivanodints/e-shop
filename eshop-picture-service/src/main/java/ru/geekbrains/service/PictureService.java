package ru.geekbrains.service;

import ru.geekbrains.controllers.DTO.PictureDTO;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.model.Product;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    Optional<Product> getProductByPictureId(long id);

    void removePicture(long id);

    Optional<PictureDTO> findPictureById (Long id);

    List<PictureDTO> showAllPictures();
}
