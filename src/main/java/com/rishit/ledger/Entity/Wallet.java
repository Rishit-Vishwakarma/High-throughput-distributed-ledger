package com.rishit.ledger.Entity;

import com.rishit.ledger.Enum.WalletStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @NotNull
    @Column(precision = 19, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletStatus status;

    @NotNull
    @Column(length = 3)
    private String currency;

    @PrePersist
    public void dateTimeAssign(){
        this.createdAt = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
