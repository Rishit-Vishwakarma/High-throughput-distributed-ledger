package com.rishit.ledger.Repository;

import com.rishit.ledger.Entity.User;
import com.rishit.ledger.Entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Boolean existsByUser_UserId(Long user_id);
}
