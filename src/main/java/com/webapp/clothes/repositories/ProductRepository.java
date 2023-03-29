package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findTop4ByOrderByIdDesc();

    @Query(value = "select p.category.id, p.category.categoryName, count(*), p.category.pathImage from Product p group by p.category.categoryName")
    List<Object[]> countProductByCategory();

    @Query(value = "select p from Product p, BillDetail b where p.id = b.product.id group by p.id ORDER BY COUNT(p.id) DESC")
    List<Product> getTopProduct();

    List<Product> findByBrand(String brand);

    @Query(value = "select count(*) from Product p group by p.color order by p.color ASC ")
    List<Integer> countProductByColor();

    List<Product> findByPriceBetween(Integer startPrice, Integer endPrice);

    List<Product> findByPriceGreaterThan(Integer price);

    List<Product> findByColor(String color);

    @Query(value = "select p from Product p where p.category.id = ?1")
    List<Product> findByCategoryId(Integer categoryId);

    @Query(value = "SELECT \n" +
            "    CASE \n" +
            "        WHEN price BETWEEN 0 AND 399999 THEN '100k-400k'\n" +
            "        WHEN price BETWEEN 400000 AND 699999 THEN '400k-700k'\n" +
            "        WHEN price > 700000 THEN 'Over 700k'\n" +
            "    END AS price_range,\n" +
            "    COUNT(*) AS quantity\n" +
            "FROM Product\n" +
            "GROUP BY price_range;", nativeQuery = true)
    List<Object[]> countProductByPrice();



}
