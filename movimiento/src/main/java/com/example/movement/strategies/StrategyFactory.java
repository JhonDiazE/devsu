/* (C) 2023 Cencosud */
package com.example.movement.strategies;


import com.example.movement.dto.MovementType;
import com.example.movement.exceptions.StrategyNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class StrategyFactory {
    private final Map<MovementType, OperationStrategy> strategies = new HashMap<>();

    public StrategyFactory(
            Set<OperationStrategy> operationStrategiesSet) {
        createStrategy(operationStrategiesSet);
    }

    public OperationStrategy findStrategy(MovementType type) {
        if (Objects.isNull(strategies.get(type))) {
            throw new StrategyNotFoundException(
                    String.format(
                            "%s. %s does not have a valid implementation for this strategy.",
                            "STRATEGY NOT FOUND", type));
        }
        return strategies.get(type);
    }

    private void createStrategy(Set<OperationStrategy> operationStrategiesSet) {
        operationStrategiesSet.forEach(
                strategy -> strategies.put(strategy.getType(), strategy));
    }
}
