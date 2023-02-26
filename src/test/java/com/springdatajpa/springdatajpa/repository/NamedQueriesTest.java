package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class NamedQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void checkByPriceNamedParamMethod(){
        Optional<Product> product = Optional.ofNullable(productRepository.checkPriceNamedParam(
                new BigDecimal("62000.00"))
        );
        product.ifPresent(System.out::println);
    }

    @Test
    void checkByPriceIndexParamMethod(){
        Optional<Product> product = Optional.ofNullable(productRepository.checkByPriceIndexParam(
                new BigDecimal("61000.00"))
        );
        product.ifPresent(System.out::println);
    }

    @Test
    void findAllProductByNameDesc(){
        List<Product> productList = productRepository.findAllProductByNameDesc();
        productList.forEach(product -> System.out.println(product.toString()));

    }

    @Test
    void findAllProductByNameAsc(){
        List<Product> productList = productRepository.findAllProductByNameAsc();
        productList.forEach(product -> System.out.println(product.toString()));

    }

    @Test
    void findProductByDescriptionNamedParam(){
        Product product = productRepository.findProductByDescriptionNamedParam("Snapdragon 8 Gen 2 based One Plus 11");
        System.out.println(product);
    }

    @Test
    void findProductByDescriptionIndexParam(){
        Product product = productRepository.findProductByDescriptionIndexParam(
                "One PLus Buds Pro 2 with 48dB Adaptive Noise Cancellation"
        );

        System.out.println(product);
    }



}
