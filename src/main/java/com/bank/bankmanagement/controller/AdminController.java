package com.bank.bankmanagement.controller;

import java.util.List;

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

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/block/{accountNo}")
    public String blockAccount(@PathVariable int accountNo) {
        boolean ok = accountService.blockAccount(accountNo);
        return ok ? "Account blocked" : "Account not found";
    }

    @PutMapping("/activate/{accountNo}")
    public String unblockAccount(@PathVariable int accountNo) {
        boolean ok = accountService.unblockAccount(accountNo);
        return ok ? "Account activated" : "Account not found";
    }
}
