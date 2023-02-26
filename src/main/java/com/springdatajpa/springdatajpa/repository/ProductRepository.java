package com.springdatajpa.springdatajpa.repository;

import com.springdatajpa.springdatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Returns the found product entry by using its name as search
     * criteria. If no product entry is found, this method
     * returns null.
     */
    Optional<Product> findByName(String name);

    /**
     * Returns an Optional which contains the found product
     * entry by using its id as search criteria. If no product entry
     * is found, this method returns an empty Optional.
     */
    Optional<Product> findById(Long Id);

    /**
     * Returns the found list of product entries whose name or description is given
     * as a method parameters. If no product entries is found, this method
     * returns an empty list.
     */

    List<Product> findByNameOrDescription(String name, String description);

    /**
     * Returns the found list of product entries whose name and description is given
     * as a method parameters. If no product entries is found, this method
     * returns an empty list.
     */

    List<Product> findByNameAndDescription(String name, String description);

    /**
     * Return the distinct product entry whose name is given as a method parameter
     *  If no product entry is found, this method returns null.
     */

    Optional<Product> findDistinctByName(String name);

    /**
     * Return the products whose price is greater than given price as method parameter
     */

    List<Product> findByPriceGreaterThan(BigDecimal price);

    /**
     * Return the products whose price is less than given price as method parameter
     */
    List<Product> findByPriceLessThan(BigDecimal price);

    /**
     * Return the filtered the product records that match the given text
     */

    List<Product> findByNameContaining(String name);

    /**
     * Return products based on SQL like condition
     */

    List<Product> findByNameLike(String name);

    /**
     * Returns a products whose price between start price and end price
     */

    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    /**
     * Returns a products whose dateCreated between start date and end date
     */
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Returns list of products based on multiple values
     */
    List<Product> findByNameIn(List<String> names);

    List<Product> findFirst2ByOrderByNameAsc();

    List<Product> findTop2ByOrderByPriceDesc();

    //Define JPQL query using @Query Annotation with index or position parameters
    @Query("SELECT p FROM Product p WHERE p.name = ?1 OR p.description = ?2")
    Optional<Product> findByNameOrDescriptionJPQLIndexParam(String name, String description);

    //Define JPQL query using @Query Annotation with named parameters
    @Query("SELECT p FROM Product p WHERE p.name = :name1 OR p.description = :description1")
    Optional<Product> findByNameOrDescriptionJPQLNamedParam(@Param("name1") String name,
                                                            @Param("description1") String description);

    //Define Native SQL using @Query annotation with Index or Position Parameters
    @Query(value = "SELECT * FROM products p WHERE p.name = ?1 OR p.description = ?2", nativeQuery = true)
    Optional<Product> findByNameOrDescriptionNativeSQLQueryIndexParam(String name, String description);

    //Define Native SQL using @Query annotation with Named Parameters
    @Query(value = "SELECT * FROM products p WHERE p.name = :name1 or p.description = :description1", nativeQuery = true)
    Optional<Product> findByNameOrDescriptionNativeSQLQueryNamedParam(@Param("name1") String name,
                                                            @Param("description1") String description);

    //Named JPQL query with Named Parameters defined in Product.class Entity
    Product checkPriceNamedParam(@Param("price1")BigDecimal price);

    //Named JPQL query with Indexed Parameters defined in Product.class Entity
    Product checkByPriceIndexParam(BigDecimal price);

    //Named JPQL query defined in Product.class Entity
    List<Product> findAllProductByNameDesc();
    List<Product> findAllProductByNameAsc();

    //Named Native query with Named parameters defined in Product.class Entity
    @Query(nativeQuery = true)
    Product findProductByDescriptionNamedParam(@Param("description1") String description);

    //Named Native query with Index parameters defined in Product.class Entity
    Product findProductByDescriptionIndexParam(String description);

}
