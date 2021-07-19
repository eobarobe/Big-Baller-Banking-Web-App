package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Table(name= "bank_accounts")
@Entity
public class UserBankAccountEntity {
    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn
    private UserEntity user;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "balance_id")
    private double balance;

    @Column(name = "account_type")
    private String accountType;


    public int getAccountId() {
        return id;
    }

    public void setAccountId(int accountId) {
        this.id = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append(" accountId='").append(id).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", balance= $'").append(balance).append('\'');
        sb.append(", accountType='").append(accountType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
