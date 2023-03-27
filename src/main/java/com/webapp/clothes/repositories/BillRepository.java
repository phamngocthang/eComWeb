package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.Bill;
import com.webapp.clothes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {


}
