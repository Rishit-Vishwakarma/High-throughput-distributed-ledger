package com.rishit.ledger.Exception;

public class CurrencyMismatchException extends RuntimeException{
    public CurrencyMismatchException(String message){
        super(message);
    }
}
