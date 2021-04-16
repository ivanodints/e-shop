package ru.geekbrains.persist.repo.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.User;

public class UserSpecification {

    public static Specification<User> userLoginLike(String login) {
        return (root, query, cb) -> cb.like(root.get("login"), login );
    }

}
