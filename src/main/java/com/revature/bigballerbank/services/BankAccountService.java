package com.revature.bigballerbank.services;

import com.revature.bigballerbank.dtos.requests.DepositRequestDTO;
import com.revature.bigballerbank.dtos.requests.WithdrawalRequestDTO;
import com.revature.bigballerbank.dtos.responses.WithdrawalResponseDTO;
import com.revature.bigballerbank.models.BankAccountEntity;
import com.revature.bigballerbank.repositories.UserAccountRepository;
import com.revature.bigballerbank.repositories.BankAccountRepository;
import com.revature.bigballerbank.repositories.UserRepository;
import com.revature.bigballerbank.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor =  @__(@Autowired))
public class BankAccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final BankAccountRepository bankAccountRepository;

    /**
     * This method is responsible for making deposits of a specified amount
     * for a specified to a specific bank account. And then recording that
     * then records the transaction into a transactions table
     * */
    public boolean deposit(DepositRequestDTO depositRequestDTO){
        BankAccountEntity accountToDepositTo = (BankAccountEntity) bankAccountRepository.findById(depositRequestDTO.getBankAccountId()).get();
        if(depositRequestDTO.getDepositAmount() > 0) {
            accountToDepositTo.setBalance(accountToDepositTo.getBalance() + depositRequestDTO.getDepositAmount());
            return true;
        }
        return false;
    }

    /***
     * This method is responsible for making withdrawals of certain amount
     * for a specified bank account. If first checks to see if the withdrawl
     * amount will cause and overdraft. If so, it returns an overdraft exception.
     * If not it successfully withdraws the amount from the account
     * */
    public WithdrawalResponseDTO withdraw(WithdrawalRequestDTO withdrawalRequestDTO){
        BankAccountEntity accountToWithdrawFrom = (BankAccountEntity) bankAccountRepository.findById(withdrawalRequestDTO.getBankAccountId()).get();
        double amountAfterWithdrawal = accountToWithdrawFrom.getBalance()-withdrawalRequestDTO.getWithdrawalAmount();
        if( amountAfterWithdrawal  > 0 ) {
            accountToWithdrawFrom.setBalance(amountAfterWithdrawal);
            return new WithdrawalResponseDTO();
        }
        return null;
    }
}
