package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<Bill, Integer> {
}
