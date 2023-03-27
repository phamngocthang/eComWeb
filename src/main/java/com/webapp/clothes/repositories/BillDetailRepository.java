package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<Bill, Integer> {
    @Query(value = "select b.product.id, sum(b.amount) as SUM from BillDetail b where b.product.brand = ?1 group by b.product.id ORDER BY SUM DESC")
    List<Object[]> getSumProductBought(String brand);
}
