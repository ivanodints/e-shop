package ru.geekbrains.service.role;

import ru.geekbrains.controller.DTO.RoleAdminDTO;

import java.util.List;
import java.util.Optional;

public interface RoleAdminService {

    List<RoleAdminDTO> showAllRoles();

    Optional<RoleAdminDTO> findRoleById(Long id);

    void delete(Long id);

    void save(RoleAdminDTO roleAdminDTO);

}
