package com.example.movement.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MovementDTO {

    private String timestamp;
    private MovementType type;
    private double amount;
    private String accountNumber;
    private StatusType statusOperation;

}
