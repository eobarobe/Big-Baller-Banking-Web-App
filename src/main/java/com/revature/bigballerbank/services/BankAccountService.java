package com.revature.bigballerbank.services;

import com.revature.bigballerbank.dtos.BankAccountDTO;
import com.revature.bigballerbank.dtos.UserAccountDTO;
import com.revature.bigballerbank.dtos.requests.DepositRequestDTO;
import com.revature.bigballerbank.dtos.requests.WithdrawalRequestDTO;
import com.revature.bigballerbank.dtos.responses.WithdrawalResponseDTO;
import com.revature.bigballerbank.models.BankAccountEntity;
import com.revature.bigballerbank.models.UserAccountEntity;
import com.revature.bigballerbank.repositories.UserAccountRepository;
import com.revature.bigballerbank.repositories.BankAccountRepository;
import com.revature.bigballerbank.repositories.UserRepository;
import com.revature.bigballerbank.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequestMapping("/account")
@AllArgsConstructor(onConstructor =  @__(@Autowired))
public class BankAccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final BankAccountRepository bankAccountRepository;



    public Set<BankAccountDTO> viewUserBankAccounts(UserAccountDTO userAccountDTO) {
        List<BankAccountEntity> listOfUserBankAccounts;
        Set<BankAccountDTO> listOfUserBankAccountsDTO;

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDTO.getUsername());

        listOfUserBankAccounts = bankAccountRepository.findAllByUserAccountEntity(userAccountEntity);
        listOfUserBankAccountsDTO = listOfUserBankAccounts.stream().map(BankAccountDTO::new).collect(Collectors.toSet());

        return listOfUserBankAccountsDTO;
    }


    /**
     * This method is responsible for making deposits of a specified amount
     * for a specified to a specific bank account. And then recording that
     * then records the transaction into a transactions table
     * */
    public boolean deposit(DepositRequestDTO depositRequestDTO){

        Optional<BankAccountEntity> optionalBankAccountEntity;
        BankAccountEntity accountToDepositTo;

        optionalBankAccountEntity = bankAccountRepository.findById(Integer.parseInt(depositRequestDTO.getBankAccountId()));
        if(optionalBankAccountEntity.isPresent()){
            accountToDepositTo = optionalBankAccountEntity.get();
            if(Double.parseDouble(depositRequestDTO.getDepositAmount()) >= 0) {
                accountToDepositTo.setBalance(Double.parseDouble(accountToDepositTo.getBalance() + depositRequestDTO.getDepositAmount()));
                bankAccountRepository.save(accountToDepositTo);
                return true;
            }

        }

        return false;
    }


    /***
     * This method is responsible for making withdrawals of certain amount
     * for a specified bank account. If first checks to see if the withdrawal
     * amount will cause and overdraft. If so, it returns an overdraft exception.
     * If not it successfully withdraws the amount from the account
     * */
    public boolean withdraw(WithdrawalRequestDTO withdrawalRequestDTO){
        Optional<BankAccountEntity> optionalBankAccountEntity;
        BankAccountEntity accountToWithdrawFrom;
        double amountAfterWithdrawal;

        optionalBankAccountEntity = bankAccountRepository.findById(withdrawalRequestDTO.getBankAccountId());
        if(optionalBankAccountEntity.isPresent()) {
            accountToWithdrawFrom = optionalBankAccountEntity.get();
            amountAfterWithdrawal = accountToWithdrawFrom.getBalance()-withdrawalRequestDTO.getWithdrawalAmount();

            if (amountAfterWithdrawal >= 0) {
                accountToWithdrawFrom.setBalance(amountAfterWithdrawal);
                bankAccountRepository.save(accountToWithdrawFrom);
                return true;
            }
        }
        return false;
    }

}
