package com.example.catalogos.logs;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogService service;

    public LogController(LogService service) { this.service = service; }

    @GetMapping
    public List<LogEntry> findAll() { return service.findAll(); }

    @GetMapping("/machine/{serialNumber}")
    public List<LogEntry> findByMachine(@PathVariable String serialNumber) {
        return service.findByMachineSerial(serialNumber);
    }

    @GetMapping("/current-shift")
    public List<LogEntry> findCurrentShift() { return service.findCurrentShift(); }

    @GetMapping("/{id}")
    public LogEntry findById(@PathVariable String id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LogEntry create(@Valid @RequestBody LogRequest request) { return service.create(request); }

    @PutMapping("/{id}")
    public LogEntry update(@PathVariable String id, @Valid @RequestBody LogRequest request) { return service.update(id, request); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { service.delete(id); }
}
