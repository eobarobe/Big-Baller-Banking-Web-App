package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public @Data class TransactionEntity {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = UserAccountEntity.class)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccountEntity userAccountEntity;

    @ManyToOne(targetEntity = BankAccountEntity.class)
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccountEntity bankAccountEntity;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;
    @Column(name = "transaction_amount", nullable = false)
    private double transactionAmount;
    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

}
