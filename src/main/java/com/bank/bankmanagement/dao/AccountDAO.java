package com.bank.bankmanagement.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bank.bankmanagement.model.Account;

@Repository
public class AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE ACCOUNT
    public void save(Account account) {
        String sql = "INSERT INTO accounts(account_no, name, pin, balance, status) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                account.getAccountNo(),
                account.getName(),
                account.getPin(),
                account.getBalance(),
                account.getStatus()
        );
    }

    // LOGIN
    public Account login(int accountNo, String pin) {
        String sql = "SELECT * FROM accounts WHERE account_no = ? AND pin = ?";
        List<Account> list = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    Account a = new Account();
                    a.setAccountNo(rs.getInt("account_no"));
                    a.setName(rs.getString("name"));
                    a.setBalance(rs.getDouble("balance"));
                    a.setStatus(rs.getString("status"));
                    return a;
                },
                accountNo,
                pin
        );
        return list.isEmpty() ? null : list.get(0);
    }

    // FIND ALL
    public List<Account> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM accounts",
                (rs, rowNum) -> {
                    Account a = new Account();
                    a.setAccountNo(rs.getInt("account_no"));
                    a.setName(rs.getString("name"));
                    a.setBalance(rs.getDouble("balance"));
                    a.setStatus(rs.getString("status"));
                    return a;
                }
        );
    }

    // UPDATE STATUS
    public boolean updateStatus(int accountNo, String status) {
        String sql = "UPDATE accounts SET status = ? WHERE account_no = ?";
        return jdbcTemplate.update(sql, status, accountNo) > 0;
    }

    // DEPOSIT
    public boolean updateBalance(int accountNo, double amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";
        return jdbcTemplate.update(sql, amount, accountNo) > 0;
    }

    // WITHDRAW
    public boolean withdrawBalance(int accountNo, double amount) {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_no = ? AND balance >= ?";
        return jdbcTemplate.update(sql, amount, accountNo, amount) > 0;
    }
}
