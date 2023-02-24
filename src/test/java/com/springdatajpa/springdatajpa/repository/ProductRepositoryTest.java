package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //create Product
        Product product = new Product();
        product.setName("One Plus 11");
        product.setDescription("Snapdragon 8 Gen 2 based One Plus 11");
        product.setSku("100OnePlus11abc");
        product.setPrice(new BigDecimal(61000.00));
        product.setActive(true);
        product.setImageURl("OnePlus11.png");

        //saveProduct
        Product savedProduct = productRepository.save(product);

        //displayProductInfo
        System.out.println(savedProduct.toString());
    }

    @Test
    void updateUsingSaveMethod(){

        //find or retrieve entity by ID
        Long id = 3L;
        Product product = productRepository.findById(id).get();

        //update entity information
        product.setImageURl("/OnePlus 11R 5G.png");
        product.setDescription("Snapdragon 8+ Gen 1 based One Plus 11R 5G");

        //save updated entity
        productRepository.save(product); // here it will use merge method of Entity Manager
    }

    @Test
    void findByMethod(){
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){

        //create Product

        Product product = new Product();
        product.setName("One Plus Buds Pro 2");
        product.setDescription("One PLus Buds Pro 2 with 48dB Adaptive Noise Cancellation");
        product.setSku("100Buds2");
        product.setPrice(new BigDecimal(12000.00));
        product.setActive(true);
        product.setImageURl("/OnePlus Buds Pro 2.png");

        //create Product
        Product product1 = new Product();
        product1.setName("One Plus 11R 5G");
        product1.setDescription("Snapdragon 8+ Gen 1 based One Plus 11 5G");
        product1.setSku("100OnePlus11R");
        product1.setPrice(new BigDecimal(44000.00));
        product1.setActive(true);
        product1.setImageURl("/OnePLus 11R %G.png");

        //saving all products
        List<Product> productList = productRepository.saveAll(List.of(product1, product));

        for(Product prod : productList)
            System.out.println(prod.toString());
    }

    @Test
    void findAllMethod(){

        List<Product> products = productRepository.findAll();
        System.out.println("\nRetrieved Products are:\n");
        products.forEach(product -> System.out.println(product.toString()));
        System.out.println();
    }

    @Test
    void deleteByIdMethod(){
        Long Id = 1L;
        productRepository.deleteById(Id);
    }

    @Test
    void deleteMethod(){
        //find entity by Id
        Long Id = 1L;
        Optional<Product> productOptional = productRepository.findById(Id);
        if(productOptional.isEmpty())
            return;

        //deleteEntity
        productRepository.delete(productOptional.get());
    }

    @Test
    void deleteAllMethod(){
        //productRepository.deleteAll();

        Product product = productRepository.findById(9L).get();
        Product product1 = productRepository.findById(10L).get();

        productRepository.deleteAll(List.of(product1, product));
    }

    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Long id = 7L;
        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }
}