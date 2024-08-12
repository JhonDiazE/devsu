package com.example.movement.repository;

import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNumber(String number);

}