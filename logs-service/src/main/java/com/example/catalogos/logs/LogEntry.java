package com.example.catalogos.logs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "log_entries")
@Getter
@Setter
@NoArgsConstructor
public class LogEntry {
    @Id
    @Column(nullable = false, updatable = false, length = 36)
    private String id;
    @Column(nullable = false, length = 80)
    private String machineSerial;
    @Column(nullable = false, length = 150)
    private String equipment;
    @Column(nullable = false, columnDefinition = "text")
    private String description;
    @Column(nullable = false, length = 150)
    private String technician;
    @Column(nullable = false)
    private LocalDate maintenanceDate;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() { timestamp = LocalDateTime.now(); }

    public String getMachineSerial() { return machineSerial; }

    public void setMachineSerial(String machineSerial) { this.machineSerial = machineSerial; }

    public String getEquipment() { return equipment; }

    public void setEquipment(String equipment) { this.equipment = equipment; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getTechnician() { return technician; }

    public void setTechnician(String technician) { this.technician = technician; }

    public LocalDate getMaintenanceDate() { return maintenanceDate; }

    public void setMaintenanceDate(LocalDate maintenanceDate) { this.maintenanceDate = maintenanceDate; }
}
