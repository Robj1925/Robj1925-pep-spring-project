package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public boolean accountExists(int id) {
        List<Account> allAccounts = accountRepository.findAll();
        for (Account acc: allAccounts) {
            if (acc.getAccount_id() == id) {
                return true;
            }
        }
        return false;
    }
    public ResponseEntity<Account> loginValidation(Account account) {
        List<Account> allAccounts = accountRepository.findAll();
        for (Account acc: allAccounts) {
            if (acc.getUsername().equals(account.getUsername())) {
                if (acc.getPassword().equals(account.getPassword())) {
                    return ResponseEntity.status(HttpStatus.OK).body(acc);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    public ResponseEntity<Account> registerAccount(Account account) {
        if (account.getUsername().isEmpty() || account.getUsername().length() < 4) {
            return ResponseEntity.status(400).body(null);
        }
        List<Account> allAccounts = accountRepository.findAll();
        for (Account acc: allAccounts) {
            if (acc.getUsername().equals(account.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

}
