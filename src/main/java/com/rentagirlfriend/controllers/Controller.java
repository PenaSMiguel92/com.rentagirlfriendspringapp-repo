package com.rentagirlfriend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.rentagirlfriend.entities.*;
import com.rentagirlfriend.exceptions.*;
import com.rentagirlfriend.services.*;

@RestController
public class Controller {
    AccountService accountService;
    MessageService messageService;
    @Autowired
    public Controller(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    //Account mappings

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

    //Message mappings

    @GetMapping("/accounts/{username}/messages")
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessagesReceivedByAccountHandler(@PathVariable("username") String username) {
        return this.messageService.getAllMessagesReceivedByUsername(username);
    }

    @PostMapping("/accounts/{username}/messages")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessageToAccountHandler(@RequestBody Message message, @PathVariable("username") String username) {
        //In future, add a way to check if poster is a valid user and has a valid login.
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
