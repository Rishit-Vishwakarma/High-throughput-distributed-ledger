package com.rishit.ledger.Exception;

public class WalletInactiveException extends RuntimeException{
    public WalletInactiveException(String message){
        super(message);
    }
}
