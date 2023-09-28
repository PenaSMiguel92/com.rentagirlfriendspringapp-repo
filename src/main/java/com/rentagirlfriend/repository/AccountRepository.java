package com.rentagirlfriend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentagirlfriend.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    
}
