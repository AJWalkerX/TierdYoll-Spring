package com.ajwalker.entity;

import com.ajwalker.utility.enums.EOrderState;
import com.ajwalker.utility.enums.EState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_order")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    EOrderState orderState;
    @Column(name = "user_id")
    Long userId;//customer
    @Column(name = "basket_id")
    Long basketId;
    @Column(name = "order_date")
    Long orderDate;
    String address;
}