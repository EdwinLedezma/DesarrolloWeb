package com.example.catalogos.clients;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock ClientRepository repository;
    @InjectMocks ClientService service;

    @Test
    void createPersistsClientFields() {
        when(repository.save(any(Client.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Client saved = service.create(new ClientRequest("Ana Pérez", "ana@example.com", "555-0100"));

        assertEquals("Ana Pérez", saved.getFullName());
        assertEquals("ana@example.com", saved.getEmail());
        assertEquals("555-0100", saved.getPhone());
    }
}
