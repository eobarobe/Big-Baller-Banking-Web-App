package com.revature.bigballerbank.dtos;

import com.revature.bigballerbank.models.BankAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class BankAccountDTO {
    private Integer bankAccountID;
    public BankAccountDTO(BankAccountEntity bankAccountEntity){
        this.bankAccountID = bankAccountEntity.getId();
    }

}
