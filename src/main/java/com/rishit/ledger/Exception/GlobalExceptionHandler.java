package com.rishit.ledger.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWalletNotFoundException(WalletNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                404,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(WalletInactiveException.class)
    public ResponseEntity<ErrorResponse> handleWalletInactiveException(WalletInactiveException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(SameWalletTransferException.class)
    public ResponseEntity<ErrorResponse> handleSameWalletTransferException(SameWalletTransferException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAmountException(InvalidAmountException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(CurrencyMismatchException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyMismatchException(CurrencyMismatchException exception){
        ErrorResponse errorResponse = new ErrorResponse(
                400,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(404).body(errorResponse);
    }
}
