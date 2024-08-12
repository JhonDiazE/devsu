/* (C) 2023 Cencosud */
package com.example.movement.exceptions;

public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException() {
        super();
    }

    public StrategyNotFoundException(String message) {
        super(message);
    }
}
