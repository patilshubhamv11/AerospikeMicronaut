package com.restapi.Model;

import com.aerospike.mapper.annotations.AerospikeBin;
import com.aerospike.mapper.annotations.AerospikeKey;
import com.aerospike.mapper.annotations.AerospikeRecord;

@AerospikeRecord(namespace = "test",set = "account")
public class Account {

    @AerospikeKey
    @AerospikeBin
    private long accountNumber;

    @AerospikeBin
    private String panNumber;

    @AerospikeBin
    private String email;

    @AerospikeBin
    private String password;

    @AerospikeBin
    private String address;

    @AerospikeBin
    private long balance;

    public Account(){}

    public Account(long accountNumber, String panNumber, String email, String password, String address, long balance) {
        this.accountNumber = accountNumber;
        this.panNumber = panNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", panNumber='" + panNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}