package com.bank.bankmanagement.model;

public class Account {

    private int accountNo;
    private String name;
    private int pin;
    private double balance;
    private String status;

    public Account() {}

    public Account(int accountNo, String name, int pin, double balance, String status) {
        this.accountNo = accountNo;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.status = status;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
