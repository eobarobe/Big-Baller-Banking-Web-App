package com.revature.bigballerbank.controllers;

import com.revature.bigballerbank.dtos.AuthenticatedDTO;
import com.revature.bigballerbank.dtos.CredentialsDTO;
import com.revature.bigballerbank.dtos.UserAccountRegisterDTO;
import com.revature.bigballerbank.services.UserAccountService;
import io.swagger.annotations.ResponseHeader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {
    private final UserAccountService userAccountService;

    @PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseHeader(name = "httpServletResponse")
    public AuthenticatedDTO login(@RequestBody CredentialsDTO credentialsDTO, HttpServletResponse httpServletResponse) {
        AuthenticatedDTO authenticatedDTO = userAccountService.login(credentialsDTO);
        return authenticatedDTO;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseHeader(name = "httpServletResponse")
    public AuthenticatedDTO register(@RequestBody UserAccountRegisterDTO userAccountRegisterDTO, HttpServletResponse httpServletResponse) {
        AuthenticatedDTO authenticatedDTO = userAccountService.register(userAccountRegisterDTO);
        return authenticatedDTO;
    }
}
