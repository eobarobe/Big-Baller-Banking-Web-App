package com.revature.bigballerbank.exceptions;
/** to be thrown when user already exists in system*/
public class DuplicateRegistrationException extends RuntimeException {
    public DuplicateRegistrationException(String message){super(message);}
}
