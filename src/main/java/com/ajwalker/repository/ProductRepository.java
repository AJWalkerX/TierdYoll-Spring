package com.ajwalker.repository;

import com.ajwalker.entity.Product;
import com.ajwalker.views.VwProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

	
	@Query("SELECT NEW com.ajwalker.views.VwProductDetails(p.name, p.productCode, p.price, p.userId, p.stock, p.categoryId) FROM Product p WHERE p.productCode = :productCode")
	Optional<VwProductDetails> findByProductCode(@Param("productCode") String productCode);

	Optional<Product> findOptionalById(Long id);

    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);

	List<Product> findTop100ByOrderByIdDesc();
}