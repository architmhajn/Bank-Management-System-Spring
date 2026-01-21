package com.bank.bankmanagement.dao;

import java.util.List;

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

    // VIEW ALL ACCOUNTS (ADMIN)
    public List<Account> getAllAccounts() {
        String sql = "SELECT account_no, name, balance, status FROM accounts";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Account(
                        rs.getInt("account_no"),
                        rs.getString("name"),
                        0,                       // PIN hidden
                        rs.getDouble("balance"),
                        rs.getString("status")
                )
        );
    }

    // UPDATE ACCOUNT STATUS (ADMIN)
    public int updateStatus(int accountNo, String status) {
        String sql = "UPDATE accounts SET status = ? WHERE account_no = ?";
        return jdbcTemplate.update(sql, status, accountNo);
    }
}
