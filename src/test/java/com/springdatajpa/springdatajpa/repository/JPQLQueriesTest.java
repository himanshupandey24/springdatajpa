package com.springdatajpa.springdatajpa.repository;


import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class JPQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionJPQLIndexParam(){
        Optional<Product> product = productRepository.findByNameOrDescriptionJPQLIndexParam(
                "One Plus 11", ""
        );

        product.ifPresent(System.out::println);

    }

    @Test
    void findByNameOrDescriptionJPQLNamedParam(){
        Optional<Product> product = productRepository.findByNameOrDescriptionJPQLNamedParam(
                "One Plus 11",
                ""
        );

        product.ifPresent(System.out::println);
    }
}
