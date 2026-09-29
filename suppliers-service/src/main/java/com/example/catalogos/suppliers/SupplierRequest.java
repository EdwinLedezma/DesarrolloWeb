package com.example.catalogos.suppliers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SupplierRequest(
        @NotBlank @Size(max = 150) String companyName,
        @NotBlank @Email @Size(max = 254) String contactEmail) {
}
