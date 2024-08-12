package com.example.client.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Person {
    @Id
    private Long id;
    private String identification;
    private String name;
    private String gender;
    private int age;
    private String direction;
    private String phone;

}
