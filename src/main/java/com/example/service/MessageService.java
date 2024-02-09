package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.azul.crs.client.Response;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;
    public ResponseEntity<String> updateMessageById(int messageId, String newMessageText) {
    if (messageRepository.existsById(messageId)) {
        if (newMessageText != null && !newMessageText.isBlank() && newMessageText.length() <= 255) {
            Message existingMessage = messageRepository.findById(messageId).orElse(null);
            if (existingMessage != null) {
                existingMessage.setMessage_text(newMessageText);
                messageRepository.save(existingMessage);
                return ResponseEntity.status(HttpStatus.OK).body("1");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}
    
    public ResponseEntity<String> deleteMessageById(int id) {
        if (messageRepository.existsById(id)) {
           messageRepository.deleteById(id); 
           return ResponseEntity.status(HttpStatus.OK).body("1");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("");
        }  
    }
    public ResponseEntity<Message> getMessageById(int id) {
        Message messageRetrieved = messageRepository.findById(id).orElse(null);
        if (messageRetrieved != null) {
            
            return ResponseEntity.status(HttpStatus.OK).body(messageRetrieved);
        } 
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    public List<Message> getAllMessagesById(int id) {
        List<Message> messages = messageRepository.findByPostedBy(id);
            return messages;
    }
    public List<Message> getAllMessages() {
        List<Message> allMessages = messageRepository.findAll();
        return allMessages;
    }
    public ResponseEntity<Message> createMessage(Message msg) {
        if (msg.getMessage_text().length() > 255 || msg.getMessage_text().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
           if (accountRepository.existsById(msg.getPosted_by())) {
                Message newAcc = messageRepository.save(msg);
                return ResponseEntity.status(HttpStatus.OK).body(newAcc);
           }
                       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  
    }
}
