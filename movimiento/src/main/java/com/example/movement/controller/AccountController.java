package com.example.movement.controller;

import com.example.movement.entity.Account;
import com.example.movement.exceptions.NotFoundException;
import com.example.movement.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    public List<Account> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Account save(@RequestBody Account account) {
        return service.save(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account account) {
        Account existingAccount = service.findById(id)
                .orElseThrow(() -> new NotFoundException("Account with id " + id + " not found"));
        account.setNumber(existingAccount.getNumber());
        service.save(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
