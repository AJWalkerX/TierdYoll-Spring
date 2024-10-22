package com.ajwalker.repository;

import com.ajwalker.entity.Product;
import com.ajwalker.views.VwProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT NEW com.ajwalker.views.VwProduct(p.name,p.productCode,p.price,p.userId,p.stock,p.category) FROM Product p")
	List<VwProduct> findAllProducts();
	
}