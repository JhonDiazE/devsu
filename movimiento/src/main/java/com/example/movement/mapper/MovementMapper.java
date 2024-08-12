package com.example.movement.mapper;

import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.StatusType;
import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.repository.MovementRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovementMapper {

    public static Movement getMovementDTO(MovementDTO movementDTO, Account account, Double newBalance) {
        movementDTO.setTimestamp(LocalDateTime.now().toString());
        return Movement.builder()
                .account(account)
                .type(movementDTO.getType())
                .amount(movementDTO.getAmount())
                .balance(newBalance)
                .timestamp(movementDTO.getTimestamp())
                .build();
    }
}
