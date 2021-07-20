package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "transactions")
public class UserTransactionEntity {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = UserAccountEntity.class)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccountEntity userAccountEntity;

    @ManyToOne(targetEntity = UserBankAccountEntity.class)
    @JoinColumn(name = "user_bank_account_entity_id", nullable = false)
    private UserBankAccountEntity userBankAccountEntity;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;
    @Column(name = "transaction_amount", nullable = false)
    private double transactionAmount;
    @Column(name = "transaction_date", nullable = false)
    private String transactionDate;

}
