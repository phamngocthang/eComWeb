package com.webapp.clothes.repositories;

import com.webapp.clothes.entity.InforAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforAccountRepository extends JpaRepository<InforAccount,String> {
}
