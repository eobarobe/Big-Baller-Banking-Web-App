package com.revature.bigballerbank.dtos;

import com.revature.bigballerbank.models.UserBankAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BankAccountDTO {
    private Integer bankAccountID;
    public BankAccountDTO(UserBankAccountEntity bankAccountEntity){
        this.bankAccountID = bankAccountEntity.getAccountId();
    }

}
