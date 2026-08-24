package com.rishit.ledger.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWalletRequest {
    @NotNull
    @Positive
    private Long userId;
    @NotBlank
    private String currency;
}
