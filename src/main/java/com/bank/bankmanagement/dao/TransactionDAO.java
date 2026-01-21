package com.bank.bankmanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bank.bankmanagement.model.Transaction;

@Repository
public class TransactionDAO {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SAVE TRANSACTION
    public void save(Transaction transaction) {

        String sql = """
            INSERT INTO transactions (account_no, type, amount, date)
            VALUES (?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                transaction.getAccountNo(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getDate()
        );
    }

    // FETCH TRANSACTION HISTORY
    public List<Transaction> findByAccountNo(int accountNo) {

        String sql = """
            SELECT * FROM transactions
            WHERE account_no = ?
            ORDER BY date DESC
        """;

        return jdbcTemplate.query(sql, this::mapRow, accountNo);
    }

    private Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction tx = new Transaction();
        tx.setId(rs.getInt("id"));
        tx.setAccountNo(rs.getInt("account_no"));
        tx.setType(rs.getString("type"));
        tx.setAmount(rs.getDouble("amount"));
        tx.setDate(rs.getTimestamp("date").toLocalDateTime());
        return tx;
    }
}
