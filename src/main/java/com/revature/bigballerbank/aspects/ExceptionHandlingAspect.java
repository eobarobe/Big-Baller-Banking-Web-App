package com.revature.bigballerbank.aspects;

import com.revature.bigballerbank.exceptions.DuplicateRegistrationException;
import com.revature.bigballerbank.exceptions.InvalidCredentialsException;
import com.revature.bigballerbank.exceptions.InvalidRequestException;
import com.revature.bigballerbank.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingAspect {
    /**
     *  This class provides spring advice for client side errors [4xx]
     *  The spring framework has its own AOP (Aspect-Oriented Program).
     *  This program is based on ApsectJ. AOP enables further modulirization
     *  which compliments OOP. Some examples of concerns are as follows:
     *      ->transaction management
     *      ->logging throughout our program
     *      ->security
     *      ->etc
     *  These concerns are often labeled as "cross-cutting"
     */

    //raises exception when invalid req has been made [400]
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleInvalidRequestException(){}

    //raises exception when in proper credentials have been made [401]
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleInvalidCredentialsException(){}

    //raises exception when user tries to make a duplicate account [409]
    @ExceptionHandler(DuplicateRegistrationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleDuplicateRegistrationException() {}

    //raises an exception when resource is not found [404]
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException() {}

}
