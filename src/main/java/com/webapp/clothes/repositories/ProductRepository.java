package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findTop4ByOrderByIdDesc();

    @Query(value = "select p.category.categoryName, count(*), p.category.pathImage from Product p group by p.category.categoryName")
    List<Object[]> countProductByCategory();

    @Query(value = "select p from Product p, BillDetail b where p.id = b.product.id group by p.id ORDER BY COUNT(p.id) DESC")
    List<Product> getTopProduct();

    @Query(value = "select p from Product p, BillDetail b where p.id = ?1 and b.id = ?1 and p.brand = ?2 ORDER BY count (p.id) DESC ")
    List<Product> getRelatedProduct(Integer id, String brand);
    List<Product> findByBrand(String brand);
}
