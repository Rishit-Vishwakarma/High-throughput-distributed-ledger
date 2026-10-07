package com.rishit.ledger.Repository;

import com.rishit.ledger.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Optional<User> findUserByUserId(Long userId);
}
