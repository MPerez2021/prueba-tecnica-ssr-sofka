package com.microservice.groupone.service;

import com.microservice.groupone.entity.Client;
import com.microservice.groupone.exception.ResourceNotFoundException;
import com.microservice.groupone.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;
    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(String id, Client client) throws ResourceNotFoundException {
        Client clientFound = findClientById(id);
        clientFound.setPassword(client.getPassword());
        clientFound.setStatus(client.isStatus());
        clientFound.setName(client.getName());
        clientFound.setGender(client.getGender());
        clientFound.setAge(client.getAge());
        clientFound.setCi(client.getCi());
        clientFound.setAddress(client.getAddress());
        clientFound.setPhoneNumber(client.getPhoneNumber());

        clientRepository.save(client);
    }

    @Override
    public Client findClientById(String id) throws ResourceNotFoundException {
        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public void deleteClientById(String id) {
        clientRepository.deleteById(id);
    }
}
