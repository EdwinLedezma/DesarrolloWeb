package com.example.catalogos.suppliers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class SupplierService {
    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Supplier> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Supplier findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado")); }

    public Supplier create(SupplierRequest request) {
        Supplier supplier = new Supplier();
        apply(supplier, request);
        return repository.save(supplier);
    }

    public Supplier update(Long id, SupplierRequest request) {
        Supplier supplier = findById(id);
        apply(supplier, request);
        return repository.save(supplier);
    }

    public void delete(Long id) { repository.delete(findById(id)); }

    private void apply(Supplier supplier, SupplierRequest request) {
        supplier.setCompanyName(request.companyName());
        supplier.setContactEmail(request.contactEmail());
    }
}
