package com.bank.bankmanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.service.AccountService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AccountService accountService;

    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    // VIEW ALL ACCOUNTS
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // BLOCK ACCOUNT
    @PutMapping("/block/{accountNo}")
    public ResponseEntity<String> blockAccount(@PathVariable int accountNo) {

        boolean blocked = accountService.blockAccount(accountNo);

        if (blocked) {
            return ResponseEntity.ok("Account blocked successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }

    // UNBLOCK ACCOUNT
    @PutMapping("/unblock/{accountNo}")
    public ResponseEntity<String> unblockAccount(@PathVariable int accountNo) {

        boolean unblocked = accountService.unblockAccount(accountNo);

        if (unblocked) {
            return ResponseEntity.ok("Account unblocked successfully");
        } else {
            return ResponseEntity.badRequest().body("Account not found");
        }
    }
}
