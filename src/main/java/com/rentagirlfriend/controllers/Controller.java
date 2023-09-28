package com.rentagirlfriend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rentagirlfriend.entities.Account;
import com.rentagirlfriend.exceptions.ClientErrorException;
import com.rentagirlfriend.exceptions.ConflictException;
import com.rentagirlfriend.exceptions.UnauthorizedException;
import com.rentagirlfriend.services.AccountService;

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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccountHandler(@RequestBody Account account) throws ClientErrorException, ConflictException {
        return this.accountService.registerAccount(account);
    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Account loginAccountHandler(@RequestBody Account account)
            throws ClientErrorException, UnauthorizedException {
        return this.accountService.loginAccount(account);
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
