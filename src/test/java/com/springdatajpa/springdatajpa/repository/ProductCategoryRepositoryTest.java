package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import com.springdatajpa.springdatajpa.entity.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    void saveProductCategory(){

        ProductCategory productCategory = ProductCategory.builder()
                .categoryName("Smartphones")
                .categoryDescription("5G Enabled High End Smartphones")
                .products(new ArrayList<>())
                .build();

        Product product3 = Product.builder()
                .name("Samsung Galaxy S23 Ultra 5G")
                .description("Samsung Galaxy S23 Ultra 5G (Phantom Black, 12GB, 512GB Storage)")
                .imageURl("/Samsung S 23 Ultra.png")
                .sku("12512S23ULTRA")
                .price(new BigDecimal("134000.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product4 = Product.builder()
                .name("Samsung Galaxy S23 5G")
                .description("Samsung Galaxy S23 5G (Phantom Black, 8GB, 256GB Storage)")
                .imageURl("/Samsung S23.png")
                .sku("8256S23")
                .price(new BigDecimal("79999.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product5 = Product.builder()
                .name("Apple Iphone 14 Pro Max")
                .description("Apple iPhone 14 Pro Max 256GB Deep Purple")
                .imageURl("/Iphone 14 Pro Max.png")
                .sku("8256I14PM")
                .price(new BigDecimal("149000.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product6 = Product.builder()
                .name("Apple Iphone 14 Pro")
                .description("Apple iPhone 14 Pro 256 GB Deep Purple")
                .imageURl("/Iphone 14 Pro.png")
                .sku("8256I14P")
                .price(new BigDecimal("129000.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product7 = Product.builder()
                .name("Apple Iphone 14 Plus")
                .description("Apple iPhone 14 Plus 128GB Blue")
                .imageURl("/Iphone 14 Plus.png")
                .sku("8128I14PL")
                .price(new BigDecimal("90999.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        Product product8 = Product.builder()
                .name("Apple Iphone 14")
                .description("Apple iPhone 14 256 GB Deep Purple")
                .imageURl("/Iphone 14.png")
                .sku("8256I14")
                .price(new BigDecimal("80999.00"))
                .active(true)
                .productCategory(productCategory)
                .build();

        productCategory.getProducts().addAll(List.of(product3, product4, product5, product6, product7, product8));

        productCategoryRepository.save(productCategory);
    }

}
