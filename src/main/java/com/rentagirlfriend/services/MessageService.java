package com.rentagirlfriend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entities.Account;
import com.rentagirlfriend.entities.Message;
import com.rentagirlfriend.repositories.AccountRepository;
import com.rentagirlfriend.repositories.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public List<Message> getAllMessagesReceivedByUsername(String username) {
        Optional<Account> accountOptional = Optional.ofNullable(this.accountRepository.findAccountByUsername(username));
        if (!accountOptional.isPresent())
            return new ArrayList<>();
        
        return this.messageRepository.getReceivedMessages(accountOptional.get().getId());
    }
    
}
