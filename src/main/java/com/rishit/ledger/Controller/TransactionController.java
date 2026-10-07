package com.rishit.ledger.Controller;

import com.rishit.ledger.DTO.Request.TransactionRequest;
import com.rishit.ledger.DTO.Response.TransactionResponse;
import com.rishit.ledger.Service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public TransactionResponse transactionResponse(@Valid @RequestBody TransactionRequest transactionRequest){
        return transactionService.transfer(transactionRequest);
    }
}
