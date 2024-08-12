package com.example.client;

import com.example.client.entity.Client;
import com.example.client.repository.ClientRepository;
import com.example.client.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

	@Mock
	private ClientRepository repository;

	@InjectMocks
	private ClientService clientService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveClient() {
		Client client = Client.builder()
				.id("1")
				.password("pass")
				.build();
		
		when(repository.save(any(Client.class))).thenReturn(client);

		Client created = clientService.save(client);

		assertEquals("pass", created.getPassword());
	}

	@Test
	public void testGetClientPorId() {
		Client client = Client.builder()
				.id("1")
				.password("pass")
				.build();

		when(repository.findById("1")).thenReturn(Optional.of(client));

		Optional<Client> found = clientService.findById("1");

		assertTrue(found.isPresent());
		assertEquals("pass", found.get().getPassword());
	}
}