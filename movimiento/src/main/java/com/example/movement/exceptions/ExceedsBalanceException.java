/* (C) 2023 Cencopay */
package com.example.movement.exceptions;

public class ExceedsBalanceException extends RuntimeException {
    public ExceedsBalanceException(String message) {
        super(message);
    }

    public ExceedsBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
