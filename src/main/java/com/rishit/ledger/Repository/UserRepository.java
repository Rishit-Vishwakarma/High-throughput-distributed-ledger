package com.rishit.ledger.Repository;

import com.rishit.ledger.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
