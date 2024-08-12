package com.example.movement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.movement.controller.MovementController;
import com.example.movement.dto.MovementDTO;
import com.example.movement.dto.MovementType;
import com.example.movement.dto.StatusType;
import com.example.movement.entity.Account;
import com.example.movement.entity.Movement;
import com.example.movement.service.MovementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

class MovementControllerTest {

    @Mock
    private MovementService service;

    @InjectMocks
    private MovementController controller;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAll() throws Exception {
        Movement movement1 = Movement.builder()
                .id(1L)
                .timestamp(LocalDateTime.now().toString())
                .type(MovementType.INCREASE)
                .amount(100.0)
                .balance(1000.0)
                .account(Account.builder()
                        .number("123")
                        .build())
                .build();

        Movement movement2 = Movement.builder()
                .id(2L)
                .timestamp(LocalDateTime.now().toString())
                .type(MovementType.DECREASE)
                .amount(50.0)
                .balance(950.0)
                .account(Account.builder()
                        .number("123")
                        .build())
                .build();

        when(service.findAll()).thenReturn(Arrays.asList(movement1, movement2));

        mockMvc.perform(get("/movements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].amount").value(50.0));

        verify(service, times(1)).findAll();
    }

    @Test
    void testSave() throws Exception {
        MovementDTO movementDTO = MovementDTO.builder()
                .timestamp(LocalDateTime.now().toString())
                .type(MovementType.INCREASE)
                .amount(100.0)
                .accountNumber("123456")
                .statusOperation(StatusType.AUTHORIZED)
                .build();

        when(service.save(any(MovementDTO.class))).thenReturn(movementDTO);

        mockMvc.perform(post("/movements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movementDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.statusOperation").value("AUTHORIZED"));

        verify(service, times(1)).save(any(MovementDTO.class));
    }

    @Test
    void testGetById() throws Exception {
        Movement movement = Movement.builder()
                .id(1L)
                .timestamp(LocalDateTime.now().toString())
                .type(MovementType.INCREASE)
                .amount(100.0)
                .balance(1000.0)
                .account(Account.builder()
                        .number("123456")
                        .build())
                .build();

        when(service.findById(1L)).thenReturn(Optional.of(movement));

        mockMvc.perform(get("/movements/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.balance").value(1000.0));

        verify(service, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/movements/1"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(1L);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/movements/1"))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }
}
