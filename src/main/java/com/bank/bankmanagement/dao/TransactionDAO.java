package com.bank.bankmanagement.dao;

import com.bank.bankmanagement.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Transaction t) {
        return jdbcTemplate.update(
            "INSERT INTO transactions(account_no, type, amount) VALUES (?, ?, ?)",
            t.getAccountNo(),
            t.getType(),
            t.getAmount()
        );
    }

    public List<Transaction> findByAccountNo(int accountNo) {
        return jdbcTemplate.query(
            "SELECT * FROM transactions WHERE account_no=?",
            (rs, rowNum) -> {
                Transaction tx = new Transaction();
                tx.setAccountNo(rs.getInt("account_no"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setDate(rs.getTimestamp("date").toLocalDateTime());
                return tx;
            },
            accountNo
        );
    }
}
