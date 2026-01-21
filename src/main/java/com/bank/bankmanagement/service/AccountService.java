package com.bank.bankmanagement.service;

import org.springframework.stereotype.Service;

import com.bank.bankmanagement.dao.AccountDAO;
import com.bank.bankmanagement.model.Account;

@Service
public class AccountService {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean createAccount(Account account) {
        return accountDAO.createAccount(account) == 1;
    }

    public boolean login(int accountNo, int pin) {
        return accountDAO.login(accountNo, pin);
    }
}
