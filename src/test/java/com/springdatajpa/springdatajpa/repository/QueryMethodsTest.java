package com.springdatajpa.springdatajpa.repository;


import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.statements.SpringFailOnTimeout;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod(){
        Optional<Product> product = productRepository.findByName("One Plus 11 5G");
        product.ifPresent(value -> System.out.println(value.toString()));
    }

    @Test
    void findByIdMethod(){
        Optional<Product> product = productRepository.findById(11L);
        product.ifPresent(System.out::println);

    }

    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("One Plus 11", "");
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription(
                "One Plus 11R 5G",
                "Snapdragon 8+ Gen 1 based One Plus 11 5G"
        );

        products.forEach(product -> System.out.println(product.toString()));

    }

    @Test
    void findDistinctByNameMethod(){
        Optional<Product> productOptional = productRepository.findDistinctByName("One Plus 11");
        productOptional.ifPresent(System.out::println);
    }

    @Test
    void findByPriceGreaterThanMethod(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(30000.00));
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByPriceLessThan(){
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(20000.0));
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByNameContainingMethod(){
        List<Product> products = productRepository.findByNameContaining("One Plus 11");
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByNameLikeMethod() {
        List<Product> products = productRepository.findByNameLike("One Plus");
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(
                new BigDecimal(20000.00), new BigDecimal(50000.00)
        );

        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findByDateCreatedBetweenMethod(){
        LocalDateTime startDate = LocalDateTime.of(2023,02,22, 19,15, 56);
        LocalDateTime endDate = LocalDateTime.of(2023, 02, 22, 20, 00, 00);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach(product -> System.out.println(product.toString()));

    }

    @Test
    void findByNameInMethod(){
        List<Product> products = productRepository.findByNameIn(List.of("One Plus 11", "One Plus 11R 5G"));
        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findFirst2ByOrderByNameAscMethod(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();

        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void findTop2ByOrderByPriceDescMethod(){
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();
        products.forEach(product -> System.out.println(product.toString()));
    }


}
