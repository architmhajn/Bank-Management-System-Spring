package com.bank.bankmanagement.service;

import com.bank.bankmanagement.dao.AccountDAO;
import com.bank.bankmanagement.dao.TransactionDAO;
import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private final TransactionDAO transactionDAO;

    public AccountService(AccountDAO accountDAO, TransactionDAO transactionDAO) {
        this.accountDAO = accountDAO;
        this.transactionDAO = transactionDAO;
    }

    // CREATE (controller expects this name)
    public Account create(Account account) {
        accountDAO.save(account);
        return account;
    }

    // LOGIN
    public Account login(int accountNo, String pin) {
        return accountDAO.login(accountNo, pin);
    }

    // ADMIN
    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }

    public boolean blockAccount(int accountNo) {
        return accountDAO.updateStatus(accountNo, "BLOCKED");
    }

    public boolean unblockAccount(int accountNo) {
        return accountDAO.updateStatus(accountNo, "ACTIVE");
    }

    // DEPOSIT
    public boolean deposit(int accountNo, double amount) {
        boolean ok = accountDAO.updateBalance(accountNo, amount);
        if (ok) {
            transactionDAO.save(new Transaction(
                    accountNo,
                    "DEPOSIT",
                    amount,
                    LocalDateTime.now()
            ));
        }
        return ok;
    }

    // WITHDRAW
    public boolean withdraw(int accountNo, double amount) {
        boolean ok = accountDAO.withdrawBalance(accountNo, amount);
        if (ok) {
            transactionDAO.save(new Transaction(
                    accountNo,
                    "WITHDRAW",
                    amount,
                    LocalDateTime.now()
            ));
        }
        return ok;
    }

    // TRANSACTIONS
    public List<Transaction> getTransactionHistory(int accountNo) {
        return transactionDAO.findByAccountNo(accountNo);
    }
}
