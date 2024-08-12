package com.example.client.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Client{

    @Id
    private Long id;
    private String clientId;
    private String password;
    private boolean status;

    @OneToOne
    private Person persona;

}