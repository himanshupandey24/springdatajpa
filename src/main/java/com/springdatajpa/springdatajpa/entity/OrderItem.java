package com.springdatajpa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_item_generator"
    )
    @SequenceGenerator(
            name = "order_item_generator",
            sequenceName = "order_item_sequence_generator",
            allocationSize = 1
    )
    private Long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //Added for One to Many Bi-Directional Mapping, default fetch type for Many to One is Eager
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}
