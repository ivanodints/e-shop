package ru.geekbrains.controller.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.geekbrains.persist.model.Role;
import ru.geekbrains.persist.model.User;


import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class UserAdminDTO {

    private Long id;


    private String login;


    private String password;


    private Set<Role> roles;

//    @JsonIgnore
//    @NotEmpty
//    private String matchingPassword;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    public String getMatchingPassword() {
//        return matchingPassword;
//    }

//    public void setMatchingPassword(String matchingPassword) {
//        this.matchingPassword = matchingPassword;
//    }

    public UserAdminDTO() {
    }

    public UserAdminDTO(@NotEmpty String login, @NotEmpty Set<Role> roles) {
        this.login = login;
        this.roles = roles;
        this.password = password;
    }

    public UserAdminDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = new HashSet<>(user.getRoles());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
