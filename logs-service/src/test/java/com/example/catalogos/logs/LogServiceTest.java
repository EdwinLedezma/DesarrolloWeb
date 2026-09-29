package com.example.catalogos.logs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {
    @Mock LogRepository repository;
    @InjectMocks LogService service;

    @Test
    void createPersistsLogMessage() {
        when(repository.save(any(LogEntry.class))).thenAnswer(invocation -> invocation.getArgument(0));
        LogEntry saved = service.create(new LogRequest("Se registró la operación"));

        assertEquals("Se registró la operación", saved.getMessage());
    }
}
