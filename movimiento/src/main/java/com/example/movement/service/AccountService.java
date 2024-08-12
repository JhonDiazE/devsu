package com.example.movement.service;

import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.repository.AccountRepository;
import com.example.movement.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account save(Account account) {
        return repository.save(account);
    }

    public Optional<Account> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}