package com.rentagirlfriend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entities.Account;
import com.rentagirlfriend.exceptions.ClientErrorException;
import com.rentagirlfriend.exceptions.ConflictException;
import com.rentagirlfriend.exceptions.UnauthorizedException;
import com.rentagirlfriend.repositories.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public Account registerAccount(Account account) throws ClientErrorException, ConflictException {
        if (account.getUsername().isBlank())
            throw new ClientErrorException("username is blank");
        if (account.getPassword().isBlank())
            throw new ClientErrorException("password is blank");
        Optional<Account> accountOptional = Optional
                .ofNullable(this.accountRepository.findAccountByUsername(account.getUsername()));
        if (accountOptional.isPresent())
            throw new ConflictException("username already exists");

        return this.accountRepository.save(account);
    }

    public Account loginAccount(Account account) throws ClientErrorException, UnauthorizedException {
        if (account.getUsername().isBlank())
            throw new ClientErrorException("username is blank");
        Optional<Account> accountOptional = Optional
                .ofNullable(this.accountRepository.findAccountByUsername(account.getUsername()));
        if (!accountOptional.isPresent())
            throw new ClientErrorException("username does not exist");
        Account existingAccount = accountOptional.get();
        if (!existingAccount.getPassword().equals(account.getPassword()))
            throw new UnauthorizedException("passwords do not match");
        
        return existingAccount;
    }
}
