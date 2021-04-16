package ru.geekbrains.persist.repo.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.Product;

import javax.persistence.criteria.JoinType;

public class ProductSpecification {

    public static Specification<Product> titleLike(String title) {
        return (root, query, cb) -> cb.like(root.get("title"),  title);
    }

    public static Specification<Product> minPrice(Integer minPrice) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(Integer maxPrice) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> equalById(long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Product> equalByCategory(long categoryId) {
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> equalByManufacturer(long manufacturerId) {
        return (root, query, cb) -> cb.equal(root.get("manufacturer").get("id"), manufacturerId);
    }

    public static Specification<Product> fetchPictures() {
        return (root, query, cb) -> {
            root.fetch("pictures", JoinType.LEFT);
            query.distinct(true);
            return cb.isTrue(cb.literal(true));
        };
    }


}