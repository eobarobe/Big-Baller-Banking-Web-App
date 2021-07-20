package com.revature.bigballerbank.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WithdrawalRequestDTO {
    private int bankAccountId;
    private double withdrawalAmount;
}
