package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }
    @GetMapping("/accounts/{account_id}/messages")
    public List<Message> getAllMessagesById(@PathVariable int account_id) {
        return messageService.getAllMessagesById(account_id);
    }
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<String> updateMessageById(@PathVariable int message_id, @RequestBody String msg) {
        return messageService.updateMessageById(message_id, msg);
    }
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable int message_id) {
        return messageService.deleteMessageById(message_id);
    }
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int message_id) {
        return messageService.getMessageById(message_id);
    }
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message msg) {
        return messageService.createMessage(msg);
    }
    @PostMapping("/login")
    public ResponseEntity<Account> loginValidation(@RequestBody Account acc) {
        return accountService.loginValidation(acc);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account acc) {
            return accountService.registerAccount(acc);     
    }

}
