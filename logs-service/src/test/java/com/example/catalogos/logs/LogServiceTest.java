package com.example.catalogos.logs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LogServiceTest {
    @Mock LogRepository repository;
    @InjectMocks LogService service;

    @Test
    void createPersistsMaintenanceFields() {
        when(repository.save(any(LogEntry.class))).thenAnswer(invocation -> invocation.getArgument(0));
        LocalDate date = LocalDate.of(2026, 10, 2);
        LogEntry saved = service.create(new LogRequest(
                "MX-101", "Torno CNC", "Cambio de banda", "María López", date));

        assertEquals("MX-101", saved.getMachineSerial());
        assertEquals("Torno CNC", saved.getEquipment());
        assertEquals("Cambio de banda", saved.getDescription());
        assertEquals("María López", saved.getTechnician());
        assertEquals(date, saved.getMaintenanceDate());
    }

    @Test
    void findByMachineUsesSerialNumberQuery() {
        LogEntry entry = new LogEntry();
        entry.setMachineSerial("MX-101");
        when(repository.findByMachineSerialOrderByMaintenanceDateDesc("MX-101"))
                .thenReturn(List.of(entry));

        List<LogEntry> logs = service.findByMachineSerial("MX-101");

        assertEquals(1, logs.size());
        assertEquals("MX-101", logs.get(0).getMachineSerial());
    }
}
