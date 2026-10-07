package com.rishit.ledger.Exception;

public class SameWalletTransferException extends RuntimeException{
    public SameWalletTransferException(String message){
        super(message);
    }
}
