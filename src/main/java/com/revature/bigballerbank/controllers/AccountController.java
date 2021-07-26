package com.revature.bigballerbank.controllers;

import com.revature.bigballerbank.dtos.*;
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
import java.util.Set;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {
    private final UserAccountService userAccountService;
    private final BankAccountService bankAccountService;


    @CrossOrigin
    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseHeader(name = "httpServletResponse")
    public AuthenticatedDTO login(@RequestBody CredentialsDTO credentialsDTO, HttpServletResponse httpServletResponse) {
        AuthenticatedDTO authenticatedDTO = userAccountService.login(credentialsDTO);
        return authenticatedDTO;
    }


    @CrossOrigin
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseHeader(name = "httpServletResponse")
    public AuthenticatedDTO register(@RequestBody UserAccountRegisterDTO userAccountRegisterDTO, HttpServletResponse httpServletResponse) {
        AuthenticatedDTO authenticatedDTO = userAccountService.register(userAccountRegisterDTO);
        return authenticatedDTO;
    }
}
