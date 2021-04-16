package ru.geekbrains.service.role;

import ru.geekbrains.controller.DTO.RoleAdminDTO;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.repo.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoleAdminServiceImpl implements RoleAdminService {

    private final RoleRepository roleRepository;

    public RoleAdminServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleAdminDTO> showAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleAdminDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleAdminDTO> findRoleById(Long id) {
        return roleRepository.findById(id)
                .map(RoleAdminDTO::new);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void save(RoleAdminDTO roleAdminDTO) {
        Role role = new Role();
        role.setId(roleAdminDTO.getId());
        role.setTitle(roleAdminDTO.getTitle());
        role.setUsers(roleAdminDTO.getUsers());
        roleRepository.save(role);
    }
}
