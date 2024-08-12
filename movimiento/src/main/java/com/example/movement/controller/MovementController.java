package com.example.movement.controller;

import com.example.movement.dto.MovementDTO;
import com.example.movement.entity.Movement;
import com.example.movement.exceptions.NotFoundException;
import com.example.movement.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class MovementController {

    private final MovementService service;

    @GetMapping
    public List<Movement> getAll() {
        return service.findAll();
    }

    @PostMapping
    public MovementDTO save(@RequestBody MovementDTO movement) {
        return service.save(movement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
