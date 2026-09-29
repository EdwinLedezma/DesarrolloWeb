package com.example.catalogos.logs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Column(nullable = false, columnDefinition = "text")
    private String message;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        timestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() { timestamp = LocalDateTime.now(); }
}
