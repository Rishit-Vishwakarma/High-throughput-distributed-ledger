package com.rishit.ledger.Mapper;

import com.rishit.ledger.DTO.Response.TransactionResponse;
import com.rishit.ledger.Entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class TransactionMapper {

    public TransactionResponse transactionResponse(Transaction transaction){
        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setSenderWalletId(transaction.getSender().getWalletId());
        transactionResponse.setReceiverWalletId(transaction.getReceiver().getWalletId());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setCreatedAt(transaction.getCreatedAt());
        transactionResponse.setCurrency(transaction.getCurrency());
        transactionResponse.setStatus(transaction.getStatus());
        transactionResponse.setTransactionId(transaction.getTransactionId());

        return transactionResponse;
    }
}
