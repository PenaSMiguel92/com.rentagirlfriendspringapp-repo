package com.rentagirlfriend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentagirlfriend.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsername(String username);
    
}
