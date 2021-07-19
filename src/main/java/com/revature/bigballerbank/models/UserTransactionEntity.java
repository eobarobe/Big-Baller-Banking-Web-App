package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class UserTransactionEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transaction_id", nullable = false)
    private int transactionId;
    @Column(name = "transactionType", nullable = false)
    private String transactionType;
    @Column(name = "transactionAmount", nullable = false)
    private double transactionAmount;
    @Column(name = "transactionDate", nullable = false)
    private String transactionDate;
    @Column(name = "accountType", nullable = false)
    private String accountType;
    @Column(name = "accountId")
    private int accountId;



    public int getUserId() {
        return id;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserTransactionHistory{");
        sb.append("userId='").append(id).append('\'');
        sb.append(", transactionID='").append(transactionId).append('\'');
        sb.append(", transactionType='").append(transactionType).append('\'');
        sb.append(", transactionAmount= $'").append(transactionAmount).append('\'');
        sb.append(", transactionDate='").append(transactionDate).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append(", accountId=").append(accountId);
        sb.append('}');
        return sb.toString();
    }
}


