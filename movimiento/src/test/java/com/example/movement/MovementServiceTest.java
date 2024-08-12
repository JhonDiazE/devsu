package com.example.movement;

import com.example.movement.dto.MovementDTO;
import com.example.movement.entity.Movement;
import com.example.movement.repository.MovementRepository;
import com.example.movement.service.MovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovementServiceTest {

	@Mock
	private MovementRepository repository;

	@InjectMocks
	private MovementService movementService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testGetMovementPorId() {
		Movement movement = Movement.builder()
				.id(1L)
				.amount(100.0)
				.build();

		when(repository.findById(1L)).thenReturn(Optional.of(movement));

		Optional<Movement> found = movementService.findById(1L);

		assertTrue(found.isPresent());
		assertEquals(100.0, found.get().getAmount());
	}
}