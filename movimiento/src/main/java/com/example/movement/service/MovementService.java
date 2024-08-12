package com.example.movement.service;

import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.MovementType;
import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.exceptions.ExceedsBalanceException;
import com.example.movement.exceptions.NotFoundException;
import com.example.movement.repository.AccountRepository;
import com.example.movement.repository.MovementRepository;
import com.example.movement.strategies.OperationStrategy;
import com.example.movement.strategies.StrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementService {

    private final MovementRepository repository;
    private final StrategyFactory strategyFactory;

    public List<Movement> findAll() {
        return repository.findAll();
    }

    public MovementDTO save(MovementDTO movementDTO) {

        OperationStrategy strategy =
                strategyFactory.findStrategy(movementDTO.getType());
        return strategy.process(movementDTO);
    }

    public Optional<Movement> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}