package com.ajwalker.entity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
@Builder
@Data
public class ProductSpecification implements Specification<Product> {

    private FilterCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case ">":
                return builder.greaterThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case "<":
                return builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case ":":
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            default:
                return null;
        }
    }

}
