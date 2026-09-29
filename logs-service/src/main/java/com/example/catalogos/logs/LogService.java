package com.example.catalogos.logs;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class LogService {
    private final LogRepository repository;

    public LogService(LogRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<LogEntry> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public LogEntry findById(String id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bitácora no encontrada")); }

    public LogEntry create(LogRequest request) {
        LogEntry entry = new LogEntry();
        entry.setMessage(request.message());
        return repository.save(entry);
    }

    public LogEntry update(String id, LogRequest request) {
        LogEntry entry = findById(id);
        entry.setMessage(request.message());
        return repository.save(entry);
    }

    public void delete(String id) { repository.delete(findById(id)); }
}
