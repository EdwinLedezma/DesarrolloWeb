package com.example.catalogos.logs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LogRequest(@NotBlank @Size(max = 10000) String message) {
}
