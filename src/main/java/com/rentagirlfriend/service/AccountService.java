package com.rentagirlfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entity.Account;
import com.rentagirlfriend.exception.ClientErrorException;
import com.rentagirlfriend.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }
    
    public Account registerAccount(Account account) throws ClientErrorException {
        if (account.getUsername().isBlank())
            throw new ClientErrorException("username is blank");
        if (account.getPassword().isBlank())
            throw new ClientErrorException("password is blank");
        
        return this.accountRepository.save(account);
    }
}
