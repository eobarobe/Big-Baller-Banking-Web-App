package com.revature.bigballerbank.exceptions;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }

}