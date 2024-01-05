package com.shop.repository;

import com.shop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    boolean existsByEmail(String email);
    Account findByEmail(String email);
}
