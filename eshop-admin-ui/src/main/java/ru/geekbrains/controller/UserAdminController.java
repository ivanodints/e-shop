package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.errors.NotFoundException;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.repo.RoleRepository;
import ru.geekbrains.controller.DTO.UserAdminDTO;
import ru.geekbrains.service.users.UserAdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
//@RequestMapping("/plague-brush")
public class UserAdminController {

    private final UserAdminService userAdminService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserAdminController(UserAdminService userAdminService, RoleRepository roleRepository) {
        this.userAdminService = userAdminService;
        this.roleRepository = roleRepository;
    }



//    @Secured({"ADMIN"})
//    @GetMapping("/users")
//    public String usersPage(Model model,
//                           @RequestParam("userLoginFilter") Optional<String> userLoginFilter,
//                           @RequestParam("page") Optional<Integer> page,
//                           @RequestParam("tableSize") Optional<Integer> tableSize,
//                           @RequestParam("sort") Optional<String> sort) {
//
//        Page<UserDTO> users = userService.findWithFilter(
//                userLoginFilter.orElse(null),
//                page.orElse(1) - 1,
//                tableSize.orElse(5),
//                sort.orElse(null)
//        );
//        model.addAttribute("users", users);
//        return "users";
//    }

    @GetMapping("/users")
    public String adminUsersPage(Model model) {
        model.addAttribute("activePage", "Users");
        model.addAttribute("users", userAdminService.showAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        return "users";
    }

    @Secured({"ADMIN"})
    @GetMapping("/user/{id}/edit")
    public String adminEditUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", userAdminService.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_form";
    }

    @Secured({"ADMIN"})
    @GetMapping("/user/create")
    public String adminCreateUser(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Users");
        model.addAttribute("user", new UserAdminDTO());
        model.addAttribute("roles", roleRepository.findAll());
        return "user_form";
    }

    @Secured({"ADMIN"})
    @PostMapping("/user")
    public String adminUpsertUser(@Valid UserAdminDTO user, Model model, BindingResult bindingResult) {
        model.addAttribute("activePage", "Users");

        if (bindingResult.hasErrors()) {
            return "user_form";
        }

        userAdminService.save(user);
        return "redirect:/admin/users";
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/user/{id}/delete")
    public String adminDeleteUser(Model model, @PathVariable("id") Long id) {
        userAdminService.delete(id);
        return "redirect:/admin/users";
    }



// Roles
    @Secured({"ADMIN"})
    @GetMapping("/roles")
    public String adminRolesPage(Model model) {
        model.addAttribute("activePage", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles";
    }
    @Secured({"ADMIN"})
    @GetMapping("/role/create")
    public String adminRoleCreatePage(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", new Role());
        return "role_form";
    }
    @Secured({"ADMIN"})
    @GetMapping("/role/{id}/edit")
    public String adminEditRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        model.addAttribute("activePage", "Roles");
        model.addAttribute("role", roleRepository.findById(id).orElseThrow(IllegalStateException::new));
        return "role_form";
    }
    @Secured({"ADMIN"})
    @DeleteMapping("/role/{id}/delete")
    public String adminDeleteRole(Model model, @PathVariable("id") Long id) {
        model.addAttribute("activePage", "Roles");
        roleRepository.deleteById(id);
        return "redirect:/admin/roles";
    }
    @Secured({"ADMIN"})
    @PostMapping("/role")
    public String adminUpsertRole(Model model, RedirectAttributes redirectAttributes, Role role) {
        model.addAttribute("activePage", "Roles");

        try {
            roleRepository.save(role);
        } catch (Exception ex) {

            redirectAttributes.addFlashAttribute("error", true);
            if (role.getId() == null) {
                return "redirect:/admin/role/create";
            }
            return "redirect:/admin/category/" + role.getId() + "/edit";
        }
        return "redirect:/admin/roles";
    }


}
