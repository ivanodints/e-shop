package ru.geekbrains.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.DTO.UserAdminDTO;
import ru.geekbrains.persist.model.User;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.persist.repo.specification.UserSpecification;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAdminServiceImpl implements UserAdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserAdminDTO> showAllUsers() {
        return userRepository.findAll().stream()
                .map(UserAdminDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserAdminDTO> findById(long id) {
        return userRepository.findById(id)
                .map(UserAdminDTO::new);
    }

    @Override
    public void save(UserAdminDTO userAdminDTO) {
        User user = new User();
        user.setId(userAdminDTO.getId());
        user.setLogin(userAdminDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userAdminDTO.getPassword()));
        user.setRoles(userAdminDTO.getRoles());
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserAdminDTO> findWithFilter(String userLoginFilter,
                                             Integer page,
                                             Integer tableSize,
                                             String sort) {

        Specification<User> spec = Specification.where(null);

        if (userLoginFilter != null && !userLoginFilter.isBlank()) {
            spec = spec.and(UserSpecification.userLoginLike(userLoginFilter));
        }
        if (sort != null && !sort.isBlank()) {
            return userRepository.findAll(spec, PageRequest.of(page, tableSize, Sort.by(sort)))
                    .map(UserAdminDTO::new);
        }
        return userRepository.findAll(spec, PageRequest.of(page, tableSize))
                .map(UserAdminDTO::new);
    }
}
