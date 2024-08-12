/* (C) 2023 Cencosud */
package com.example.movement.strategies;

import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.MovementType;

public interface OperationStrategy {

    MovementDTO process(MovementDTO movementDTO) ;
    MovementType getType();

}
