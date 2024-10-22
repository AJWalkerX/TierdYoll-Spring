package com.ajwalker.repository;

import com.ajwalker.entity.Product;
import com.ajwalker.views.VwProduct;
import com.ajwalker.views.VwProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT NEW com.ajwalker.views.VwProduct(p.name,p.productCode,p.category) FROM Product p")
	List<VwProduct> findAllProducts();
	
	@Query("SELECT NEW com.ajwalker.views.VwProductDetails(p.name, p.productCode, p.price, p.userId, p.stock, p.category) FROM Product p WHERE p.productCode = :productCode")
	Optional<VwProductDetails> findByProductCode(@Param("productCode") String productCode);
	
}