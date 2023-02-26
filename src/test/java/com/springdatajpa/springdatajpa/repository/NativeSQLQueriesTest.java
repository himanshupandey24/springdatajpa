package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class NativeSQLQueriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionNativeSQLQueryIndexParamMethod(){
        Optional<Product> product = productRepository.findByNameOrDescriptionNativeSQLQueryIndexParam(
                "One Plus 11 5G",
                ""
        );
        product.ifPresent(System.out::println);
    }

    @Test
    void findByNameOrDescriptionNativeSQLQueryNamedParamMethod(){
        Optional<Product> product = productRepository.findByNameOrDescriptionNativeSQLQueryNamedParam(
                "One Plus 11",
                ""
        );
        product.ifPresent(System.out::println);
    }

}
