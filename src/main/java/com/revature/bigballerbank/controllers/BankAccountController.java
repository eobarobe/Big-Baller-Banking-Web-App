package com.revature.bigballerbank.controllers;

import com.revature.bigballerbank.dtos.BankAccountDTO;
import com.revature.bigballerbank.dtos.UserAccountDTO;
import com.revature.bigballerbank.dtos.requests.DepositRequestDTO;
import com.revature.bigballerbank.dtos.requests.WithdrawalRequestDTO;
import com.revature.bigballerbank.services.BankAccountService;
import com.revature.bigballerbank.services.UserAccountService;
import io.swagger.annotations.ResponseHeader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH}, allowedHeaders = {"Content-Type"})
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/account")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final UserAccountService userAccountService;

    @GetMapping(value="/get")
    @ResponseHeader(name = "httpServletResponse")
    public void get(HttpServletResponse httpServletResponse){
        System.out.println("we in this hoe");
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/view", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseHeader(name = "httpServletResponse")
    public @ResponseBody Set<BankAccountDTO> viewAccounts(@RequestBody UserAccountDTO userAccountDTO, HttpServletRequest httpServletRequest) {

        return bankAccountService.viewUserBankAccounts(userAccountDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deposit(@RequestBody DepositRequestDTO depositRequestDTO, HttpServletResponse httpServletResponse) {
        System.out.println("inside deposit");
        if (!bankAccountService.deposit(depositRequestDTO)) {
            httpServletResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseHeader(name = "httpServletResponse")
    public void withdraw(@RequestBody WithdrawalRequestDTO withdrawalRequestDTO, HttpServletResponse httpServletResponse) {
        if (!bankAccountService.withdraw(withdrawalRequestDTO)){
            httpServletResponse.setStatus(HttpStatus.NOT_MODIFIED.value());
        }
    }
}
