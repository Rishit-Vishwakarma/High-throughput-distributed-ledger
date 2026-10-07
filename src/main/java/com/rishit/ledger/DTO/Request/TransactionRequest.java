package com.rishit.ledger.DTO.Request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {

    @NotNull
    @Positive
    private Long senderWalletId;

    @Positive
    @NotNull
    private Long receiverWalletId;

    @NotNull
    @Positive
    private BigDecimal amount;


    @NotBlank
    @Size(min = 3, max = 3)
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Currency must be a 3-letter uppercase code"
    )
    private String currency;
}
