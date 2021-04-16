package ru.geekbrains.service.category;

import ru.geekbrains.controller.DTO.CategoryAdminDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryAdminService {

    List<CategoryAdminDTO> showAllCategories();

    Optional<CategoryAdminDTO> findById(Long id);

    void delete(Long id);

    void save(CategoryAdminDTO categoryAdminDTO);

}
