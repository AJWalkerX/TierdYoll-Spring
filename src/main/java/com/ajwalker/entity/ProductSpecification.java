package com.ajwalker.entity;

import com.ajwalker.dto.request.ProductFilterDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> filterBy(List<ProductFilterDto> dtoList) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (ProductFilterDto filter : dtoList) {
                Predicate predicate;
                switch (filter.operation()) {
                    case EQUALS -> predicate = criteriaBuilder.equal(root.get(filter.columnName()), filter.columnValue());
                    case LIKE -> predicate = criteriaBuilder.like(root.get(filter.columnName()), "%" + filter.columnValue() + "%");
                    case GREATER_THAN -> predicate = criteriaBuilder.greaterThan(root.get(filter.columnName()), (Comparable) filter.columnValue());
                    case LESS_THAN -> predicate = criteriaBuilder.lessThan(root.get(filter.columnName()), (Comparable) filter.columnValue());
                    default -> throw new UnsupportedOperationException("Unsupported operation: " + filter.operation());
                }
                predicates.add(predicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
