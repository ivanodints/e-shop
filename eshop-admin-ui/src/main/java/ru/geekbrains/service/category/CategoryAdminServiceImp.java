package ru.geekbrains.service.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.controller.DTO.CategoryAdminDTO;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryAdminServiceImp implements CategoryAdminService {

    private final CategoryRepository categoryRepository;

    public CategoryAdminServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryAdminDTO> showAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryAdminDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryAdminDTO> findById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryAdminDTO::new);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void save(CategoryAdminDTO categoryAdminDTO) {

        Category category = new Category();
        category.setId(categoryAdminDTO.getId());
        category.setTitle(categoryAdminDTO.getTitle());
        categoryRepository.save(category);

    }
}
