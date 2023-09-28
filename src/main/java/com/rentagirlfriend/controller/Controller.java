package com.rentagirlfriend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rentagirlfriend.entity.Account;
import com.rentagirlfriend.exception.ClientErrorException;
import com.rentagirlfriend.exception.ConflictException;
import com.rentagirlfriend.exception.UnauthorizedException;
import com.rentagirlfriend.service.AccountService;

@RestController
public class Controller {
    AccountService accountService;
    @Autowired
    public Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccountsHandler() {
        return this.accountService.getAllAccounts();
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccountHandler(@RequestBody Account account) throws ClientErrorException {
        Optional<Account> accountOptional = Optional.ofNullable(this.accountService.registerAccount(account));
        if (accountOptional.isPresent())
            return accountOptional.get();

        return null;
    }
    
    @ExceptionHandler(ClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String clientErrorExceptionHandler(ClientErrorException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedExceptionHandler(UnauthorizedException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictExceptionHandler(ConflictException ex) {
        return ex.getMessage();
    }
}
