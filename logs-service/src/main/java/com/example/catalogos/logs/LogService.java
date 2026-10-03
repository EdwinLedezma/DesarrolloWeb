package com.example.catalogos.logs;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@Service
public class LogService {
    private final LogRepository repository;

    public LogService(LogRepository repository) { this.repository = repository; }

    public List<LogEntry> findAll() { return repository.findAll(); }

    public LogEntry findById(String id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bitácora no encontrada")); }

    public List<LogEntry> findByMachineSerial(String machineSerial) {
        return repository.findByMachineSerialOrderByMaintenanceDateDesc(machineSerial);
    }

    public List<LogEntry> findCurrentShift() {
        return repository.findByMaintenanceDateOrderByTimestampDesc(LocalDate.now());
    }

    public LogEntry create(LogRequest request) {
        LogEntry entry = new LogEntry();
        apply(entry, request);
        return repository.save(entry);
    }

    public LogEntry update(String id, LogRequest request) {
        LogEntry entry = findById(id);
        apply(entry, request);
        return repository.save(entry);
    }

    public void delete(String id) { repository.delete(findById(id)); }

    private void apply(LogEntry entry, LogRequest request) {
        entry.setMachineSerial(request.machineSerial());
        entry.setEquipment(request.equipment());
        entry.setDescription(request.description());
        entry.setTechnician(request.technician());
        entry.setMaintenanceDate(request.maintenanceDate() == null ? LocalDate.now() : request.maintenanceDate());
    }
}
