package ru.geekbrains.service.users;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.DTO.UserAdminDTO;

import java.util.List;
import java.util.Optional;

public interface UserAdminService {

    List<UserAdminDTO> showAllUsers();

    Optional<UserAdminDTO> findById(long id);

    void save(UserAdminDTO user);

    void delete(long id);

    Page<UserAdminDTO> findWithFilter(String usernameFilter, Integer page, Integer tableSize, String sort);
}
