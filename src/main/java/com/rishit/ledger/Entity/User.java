package com.rishit.ledger.Entity;

import com.rishit.ledger.Enum.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(nullable = false, length = 100)
    private String username;

    @NotNull
    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void dateTimeAssign(){
        this.createdAt = LocalDateTime.now();
    }
}
