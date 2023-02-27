package com.springdatajpa.springdatajpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_generator"
    )
    @SequenceGenerator(
            name = "category_generator",
            sequenceName = "product_category_sequence",
            allocationSize = 1
    )
    private Long id;
    private String categoryName;
    private String categoryDescription;
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY,
            mappedBy = "productCategory"
    )
    private List<Product> products = new ArrayList<>();
}
