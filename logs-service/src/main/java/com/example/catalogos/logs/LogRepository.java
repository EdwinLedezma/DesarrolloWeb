package com.example.catalogos.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends JpaRepository<LogEntry, String> {
    List<LogEntry> findByMachineSerialOrderByMaintenanceDateDesc(String machineSerial);

    List<LogEntry> findByMaintenanceDateOrderByTimestampDesc(LocalDate maintenanceDate);
}
