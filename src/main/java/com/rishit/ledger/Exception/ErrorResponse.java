package com.rishit.ledger.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
//@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}
