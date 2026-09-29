package com.example.catalogos.suppliers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {
    @Mock SupplierRepository repository;
    @InjectMocks SupplierService service;

    @Test
    void createPersistsSupplierFields() {
        when(repository.save(any(Supplier.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Supplier saved = service.create(new SupplierRequest("Distribuidora Central", "ventas@example.com"));

        assertEquals("Distribuidora Central", saved.getCompanyName());
        assertEquals("ventas@example.com", saved.getContactEmail());
    }
}
