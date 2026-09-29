package com.example.catalogos.clients;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Client> findAll() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Client findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")); }

    public Client create(ClientRequest request) {
        Client client = new Client();
        apply(client, request);
        return repository.save(client);
    }

    public Client update(Long id, ClientRequest request) {
        Client client = findById(id);
        apply(client, request);
        return repository.save(client);
    }

    public void delete(Long id) { repository.delete(findById(id)); }

    private void apply(Client client, ClientRequest request) {
        client.setFullName(request.fullName());
        client.setEmail(request.email());
        client.setPhone(request.phone());
    }
}
