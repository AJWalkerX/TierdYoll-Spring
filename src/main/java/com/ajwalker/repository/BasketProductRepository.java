package com.ajwalker.repository;

import com.ajwalker.entity.BasketProduct;
import com.ajwalker.utility.enums.EState;
import com.ajwalker.views.VwGetBasketProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {

    @Query("SELECT NEW com.ajwalker.views.VwGetBasketProduct(bp.basketId, bp.productId, bp.quantity, bp.unitPrice, SUM(bp.totalPrice)) " +
            "FROM BasketProduct bp " +
            "WHERE bp.basketId = :basketId AND bp.state = :state " +
            "GROUP BY bp.basketId, bp.productId, bp.quantity, bp.unitPrice")
    List<VwGetBasketProduct> findAllByBasketIdAndState(@Param("basketId") Long basketId, @Param("state") EState state);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stock = p.stock - :quantity WHERE p.id = :productId")
    void updateProductStock(@Param("productId") Long productId, @Param("quantity") Long quantity);



}