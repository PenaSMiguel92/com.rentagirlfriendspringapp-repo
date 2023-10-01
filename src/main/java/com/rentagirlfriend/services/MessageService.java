package com.rentagirlfriend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entities.Message;
import com.rentagirlfriend.repositories.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessagesReceived(long received_by) {
        return this.messageRepository.getReceivedMessages(received_by);
    }
    
}
