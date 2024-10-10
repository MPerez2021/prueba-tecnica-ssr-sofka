package com.microservice.groupone.service;

import com.microservice.groupone.entity.Client;
import com.microservice.groupone.exception.ResourceNotFoundException;

public interface ClientService {

    void createClient(Client client);

    void updateClient(String id, Client client) throws ResourceNotFoundException;

    Client findClientById(String id) throws ResourceNotFoundException;

    void deleteClientById(String id);

}
