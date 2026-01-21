package com.bank.bankmanagement.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bank.bankmanagement.model.Account;
import com.bank.bankmanagement.util.HashUtil;

@Repository
public class AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE ACCOUNT
    public int createAccount(Account account) {

        String sql = """
            INSERT INTO accounts (account_no, name, pin, balance, status)
            VALUES (?, ?, ?, ?, ?)
        """;

        return jdbcTemplate.update(
                sql,
                account.getAccountNo(),
                account.getName(),
                HashUtil.hashPin(String.valueOf(account.getPin())),
                0.0,
                "ACTIVE"
        );
    }

    // LOGIN
    public boolean login(int accountNo, int pin) {

        String sql = """
            SELECT COUNT(*) FROM accounts
            WHERE account_no = ? AND pin = ? AND status = 'ACTIVE'
        """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                accountNo,
                HashUtil.hashPin(String.valueOf(pin))
        );

        return count != null && count == 1;
    }
}
