package com.rishit.ledger.Entity;

import com.rishit.ledger.Enum.LedgerEntryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ledgerId;

    @NotNull
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LedgerEntryType ledgerEntryType;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void dateTimeAssign(){
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
