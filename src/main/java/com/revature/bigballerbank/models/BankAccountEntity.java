package com.revature.bigballerbank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "bank_accounts")
public @Data class BankAccountEntity {
    @Id
    @Column(name = "bank_account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = UserAccountEntity.class)
    @JoinColumn(name = "user_account_id")
    private UserAccountEntity userAccountEntity;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_type")
    private String accountType;

}
