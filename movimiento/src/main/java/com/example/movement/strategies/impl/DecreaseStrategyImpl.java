package com.example.movement.strategies.impl;


import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.MovementType;
import com.example.movement.dto.StatusType;
import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.exceptions.ExceedsBalanceException;
import com.example.movement.exceptions.NotFoundException;
import com.example.movement.mapper.MovementMapper;
import com.example.movement.repository.AccountRepository;
import com.example.movement.repository.MovementRepository;
import com.example.movement.strategies.OperationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
@RequiredArgsConstructor
public class DecreaseStrategyImpl implements OperationStrategy {


    private final MovementRepository repository;
    private final AccountRepository accountRepository;


    public MovementDTO process(MovementDTO movementDTO){
        Account account = accountRepository.findByNumber(movementDTO.getAccountNumber())
                .orElseThrow(() -> new NotFoundException("Account with number " + movementDTO.getAccountNumber() + " not found"));
        Double newBalance = account.getAvailableBalance();
        newBalance = newBalance - movementDTO.getAmount();
        if (newBalance < 0)
            throw new ExceedsBalanceException("Amount exceeds the balance");

        account.setAvailableBalance(newBalance);
        Movement movement = MovementMapper.getMovementDTO(movementDTO, account, newBalance);
        repository.save(movement);

        movementDTO.setStatusOperation(StatusType.AUTHORIZED);
        return movementDTO;
    }



    @Override
    public MovementType getType() {
        return MovementType.DECREASE;
    }

}
