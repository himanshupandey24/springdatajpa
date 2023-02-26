package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void pagination(){
        int pageNo = 1, pageSize = 5;

        //Create Pageable Object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        //findAll method and pass the pageable instance
        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();
        products.forEach(System.out::println);

        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        int numberOfElements = page.getNumberOfElements();
        int size = page.getSize();
        boolean isLast = page.isLast();
        boolean isFirst = page.isFirst();

        System.out.println("total page -> " + totalPages);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
    }

    @Test
    void sorting(){
        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        List<Product> sortedProducts = productRepository.findAll(sort);
        sortedProducts.forEach(System.out::println);
    }

    @Test
    void sortingByMultipleFields(){

        String sortByN = "name";
        String sortByP = "price";
        String sortDir = "asc";

        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByN).ascending() : Sort.by(sortByN).descending();

        Sort sortByPrice = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByP).ascending() : Sort.by(sortByP).descending();

        Sort groupBySort = sortByName.and(sortByPrice);

        List<Product> productList = productRepository.findAll(groupBySort);

        productList.forEach(System.out::println);

    }

    @Test
    void paginationAndSortingTogether(){

        String sortByP = "price";
        String sortDir = "desc";
        int pageNo = 1, pageSize = 4;

        //Sort Object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByP).ascending() : Sort.by(sortByP).descending();

        //Pageable Object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> productPage = productRepository.findAll(pageable);

        List<Product> products = productPage.getContent();

        products.forEach(System.out::println);

        int totalPages = productPage.getTotalPages();
        long totalElements = productPage.getTotalElements();
        int numberOfElements = productPage.getNumberOfElements();
        int size = productPage.getSize();
        boolean isLast = productPage.isLast();
        boolean isFirst = productPage.isFirst();

        System.out.println("total page -> " + totalPages);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
    }
}
