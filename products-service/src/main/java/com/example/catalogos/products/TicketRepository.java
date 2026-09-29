package com.example.catalogos.products;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByNameContainingIgnoreCase(String name);
}
