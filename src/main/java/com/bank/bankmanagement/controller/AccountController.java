package com.bank.bankmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // CREATE ACCOUNT API
    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account account) {

        boolean created = accountService.createAccount(account);

        if (created) {
            return ResponseEntity.ok("Account created successfully");
        } else {
            return ResponseEntity.badRequest().body("Account creation failed");
        }
    }

    // LOGIN API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {

        boolean success = accountService.login(
                account.getAccountNo(),
                account.getPin()
        );

        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
