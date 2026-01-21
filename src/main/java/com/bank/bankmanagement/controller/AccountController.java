package com.bank.bankmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping
    public String createAccount(@RequestBody Account account) {
        accountService.create(account);
        return "Account created";
    }

    @PostMapping("/login")
    public Account login(@RequestParam int accountNo,
                         @RequestParam String pin) {
        return accountService.login(accountNo, pin);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }
}
