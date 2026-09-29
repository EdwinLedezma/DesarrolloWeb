package com.example.catalogos.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock ProductRepository repository;
    @InjectMocks ProductServiceImpl service;

    @Test
    void createPersistsProductFields() {
        when(repository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProductRequest request = new ProductRequest("Teclado", "Mecánico", new BigDecimal("49.99"), 20);

        Product saved = service.create(request);

        assertEquals("Teclado", saved.getName());
        assertEquals(new BigDecimal("49.99"), saved.getPrice());
        assertEquals(20, saved.getStock());
        verify(repository).save(any(Product.class));
    }
}
