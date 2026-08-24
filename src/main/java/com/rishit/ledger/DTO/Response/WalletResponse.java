package com.rishit.ledger.DTO.Response;

import com.rishit.ledger.Enum.WalletStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class WalletResponse {
    private Long walletId;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private WalletStatus status;
    private String currency;
}
