package com.bank.bankmanagement.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bank.bankmanagement.model.Account;

@Repository
public class AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Account> rowMapper = (rs, rowNum) -> {
        Account a = new Account();
        a.setAccountNo(rs.getInt("account_no"));
        a.setName(rs.getString("name"));
        a.setPin(rs.getString("pin"));
        a.setBalance(rs.getDouble("balance"));
        a.setStatus(rs.getString("status"));
        return a;
    };

    public int save(Account acc) {
        return jdbcTemplate.update(
                "INSERT INTO accounts(account_no,name,pin,balance,status) VALUES (?,?,?,?,?)",
                acc.getAccountNo(),
                acc.getName(),
                acc.getPin(),
                acc.getBalance(),
                "ACTIVE"
        );
    }

    public Account login(int accountNo, String pin) {
        List<Account> list = jdbcTemplate.query(
                "SELECT * FROM accounts WHERE account_no=? AND pin=? AND status='ACTIVE'",
                rowMapper,
                accountNo,
                pin
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * FROM accounts", rowMapper);
    }
}
