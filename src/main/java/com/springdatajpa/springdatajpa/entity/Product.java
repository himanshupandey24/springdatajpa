package com.springdatajpa.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
//JPQL Named Queries with Named and Indexed Param
@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.checkPriceNamedParam",
                        query = "SELECT p FROM Product p WHERE p.price = : price1"
                ),

                @NamedQuery(
                        name = "Product.checkByPriceIndexParam",
                        query = "SELECT p FROM Product p WHERE p.price = ?1"
                ),
                @NamedQuery(
                        name = "Product.findAllProductByNameDesc",
                        query = "SELECT p FROM Product p ORDER BY p.name DESC"
                ),
                @NamedQuery(
                        name = "Product.findAllProductByNameAsc",
                        query = "SELECT p FROM Product p ORDER BY p.name"
                )
        }

)

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Product.findProductByDescriptionNamedParam",
                        query = "SELECT * FROM products p WHERE p.description = :description1",
                        resultClass = Product.class
                ),

                @NamedNativeQuery(
                        name = "Product.findProductByDescriptionIndexParam",
                        query = "SELECT * FROM products p WHERE p.description = ?1",
                        resultClass = Product.class
                )
        }
)
@Table(
        name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)

public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )
    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private Long Id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageURl;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}
