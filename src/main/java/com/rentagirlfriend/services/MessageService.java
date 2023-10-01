package com.rentagirlfriend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entities.Account;
import com.rentagirlfriend.entities.Message;
import com.rentagirlfriend.exceptions.ClientErrorException;
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
    
    public void sendMessage(Message message, String username) throws ClientErrorException {
        if (message.getMessage_text().isBlank())
            throw new ClientErrorException("message text is empty.");
        if (message.getMessage_text().length() > 255)
            throw new ClientErrorException("message is too long.");
        Optional<Account> accountOptional = Optional.ofNullable(this.accountRepository.findAccountByUsername(username));
        if (!accountOptional.isPresent())
            throw new ClientErrorException("username does not exist.");
        
        this.messageRepository.save(message);
    }
    
}
