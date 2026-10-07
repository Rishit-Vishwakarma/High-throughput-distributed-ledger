package com.rishit.ledger.Service;

import com.rishit.ledger.DTO.Request.TransactionRequest;
import com.rishit.ledger.DTO.Response.TransactionResponse;
import com.rishit.ledger.Entity.LedgerEntry;
import com.rishit.ledger.Entity.Transaction;
import com.rishit.ledger.Entity.Wallet;
import com.rishit.ledger.Enum.LedgerEntryType;
import com.rishit.ledger.Enum.TransactionStatus;
import com.rishit.ledger.Enum.WalletStatus;
import com.rishit.ledger.Exception.*;
import com.rishit.ledger.Mapper.TransactionMapper;
import com.rishit.ledger.Repository.LedgerEntryRepository;
import com.rishit.ledger.Repository.TransactionRepository;
import com.rishit.ledger.Repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;
    private LedgerEntryRepository ledgerEntryRepository;
    private TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository, LedgerEntryRepository ledgerEntryRepository, TransactionMapper transactionMapper){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
        this.transactionMapper = transactionMapper;
    }

    @Transactional
    public TransactionResponse transfer(TransactionRequest transactionRequest){
        Long senderWalletId = transactionRequest.getSenderWalletId();
        Long receiverWalletId = transactionRequest.getReceiverWalletId();



        if(senderWalletId.equals(receiverWalletId)){
            throw new SameWalletTransferException("Same wallet detected!");
        }

        Wallet senderWallet;
        Wallet receiverWallet;

        if(senderWalletId.compareTo(receiverWalletId) < 0){
            senderWallet = walletRepository.findByWalletId(senderWalletId).orElseThrow(() -> new WalletNotFoundException("Sender wallet not found."));
            receiverWallet = walletRepository.findByWalletId(receiverWalletId).orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found."));
        }else {
            receiverWallet = walletRepository.findByWalletId(receiverWalletId).orElseThrow(() -> new WalletNotFoundException("Receiver wallet not found"));
            senderWallet = walletRepository.findByWalletId(senderWalletId).orElseThrow(() -> new WalletNotFoundException("Sender wallet not found."));
        }

        if(!senderWallet.getStatus().equals(WalletStatus.ACTIVE) || !receiverWallet.getStatus().equals(WalletStatus.ACTIVE)){
            throw new WalletInactiveException("Your wallet is either Freeze or Inactive.");
        }

        if(!transactionRequest.getCurrency().equals(senderWallet.getCurrency()) || !transactionRequest.getCurrency().equals(receiverWallet.getCurrency())){
            throw new CurrencyMismatchException(
                    "Currency Mismatch. Sender currency: "
                            + senderWallet.getCurrency()
                            + " Receiver currency: "
                            + receiverWallet.getCurrency());
        }

        if(transactionRequest.getAmount() == null || transactionRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Amount should be valid.");
        }


        if(senderWallet.getBalance().compareTo(transactionRequest.getAmount()) < 0){
            throw new InsufficientBalanceException("You do not have enough balance to make this transaction. Your current balance is : " + senderWallet.getBalance());
        }

        BigDecimal senderBalance = senderWallet.getBalance();
        BigDecimal receiverBalance = receiverWallet.getBalance();

        senderWallet.setBalance(senderBalance.subtract(transactionRequest.getAmount()));
        receiverWallet.setBalance(receiverBalance.add(transactionRequest.getAmount()));

        Transaction transaction = new Transaction();

        transaction.setAmount(transactionRequest.getAmount());
        transaction.setCurrency(transactionRequest.getCurrency());
        transaction.setSender(senderWallet);
        transaction.setReceiver(receiverWallet);
        transaction.setStatus(TransactionStatus.PENDING);

        Transaction savedTransaction = transactionRepository.save(transaction);

        //Ledger for Debit

        LedgerEntry debitEntry = new LedgerEntry();

        debitEntry.setWallet(senderWallet);
        debitEntry.setTransaction(savedTransaction);
        debitEntry.setDebit(transactionRequest.getAmount());
        debitEntry.setCredit(BigDecimal.ZERO);
        debitEntry.setLedgerEntryType(LedgerEntryType.DEBIT);

        ledgerEntryRepository.save(debitEntry);

        //Ledger for Credit

        LedgerEntry creditEntry = new LedgerEntry();

        creditEntry.setWallet(receiverWallet);
        creditEntry.setTransaction(savedTransaction);
        creditEntry.setDebit(BigDecimal.ZERO);
        creditEntry.setCredit(transactionRequest.getAmount());
        creditEntry.setLedgerEntryType(LedgerEntryType.CREDIT);

        ledgerEntryRepository.save(creditEntry);

        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);

        return transactionMapper.transactionResponse(transaction);
    }
}
