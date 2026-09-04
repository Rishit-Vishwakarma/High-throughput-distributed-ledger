package com.rishit.ledger.DTO.Response;

import com.rishit.ledger.Enum.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private UserStatus status;
    private LocalDateTime createdAt;
}
