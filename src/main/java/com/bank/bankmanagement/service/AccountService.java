package com.bank.bankmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.bankmanagement.dao.AccountDAO;
import com.bank.bankmanagement.model.Account;

@Service
public class AccountService {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void create(Account account) {
        accountDAO.save(account);
    }

    public Account login(int accountNo, String pin) {
        return accountDAO.login(accountNo, pin);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }
}
