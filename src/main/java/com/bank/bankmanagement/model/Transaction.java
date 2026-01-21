package com.bank.bankmanagement.model;

import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private int accountNo;
    private String type;
    private double amount;
    private LocalDateTime date;

    // REQUIRED: Empty constructor (Spring/JDBC)
    public Transaction() {
    }

    // OLD constructor (keep it)
    public Transaction(int accountNo, String type, double amount) {
        this.accountNo = accountNo;
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    // ✅ ADD THIS CONSTRUCTOR (THIS FIXES THE ERROR)
    public Transaction(int accountNo, String type, double amount, LocalDateTime date) {
        this.accountNo = accountNo;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // ===== GETTERS & SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
