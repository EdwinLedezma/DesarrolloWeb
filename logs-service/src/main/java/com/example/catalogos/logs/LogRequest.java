package com.example.catalogos.logs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record LogRequest(
        @NotBlank @Size(max = 80) String machineSerial,
        @NotBlank @Size(max = 150) String equipment,
        @NotBlank @Size(max = 10000) String description,
        @NotBlank @Size(max = 150) String technician,
        LocalDate maintenanceDate) {
}
