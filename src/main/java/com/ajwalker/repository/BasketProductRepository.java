package com.ajwalker.repository;

import com.ajwalker.entity.BasketProduct;
import com.ajwalker.utility.enums.EState;
import com.ajwalker.views.VwGetBasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {

    @Query("SELECT NEW com.ajwalker.views.VwGetBasketProduct(bp.basketId, bp.productId, bp.quantity, bp.unitPrice, SUM(bp.totalPrice)) " +
            "FROM BasketProduct bp " +
            "WHERE bp.basketId = :basketId AND bp.state = :state " +
            "GROUP BY bp.basketId, bp.productId, bp.quantity, bp.unitPrice")
    List<VwGetBasketProduct> findAllByBasketIdAndState(@Param("basketId") Long basketId, @Param("state") EState state);

}