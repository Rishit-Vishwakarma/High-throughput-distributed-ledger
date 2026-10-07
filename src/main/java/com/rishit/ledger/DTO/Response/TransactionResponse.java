package com.rishit.ledger.DTO.Response;

import com.rishit.ledger.Enum.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponse {
        private Long transactionId;
        private Long senderWalletId;
        private Long receiverWalletId;
        private BigDecimal amount;
        private TransactionStatus status;
        private String currency;
        private LocalDateTime createdAt;
}
