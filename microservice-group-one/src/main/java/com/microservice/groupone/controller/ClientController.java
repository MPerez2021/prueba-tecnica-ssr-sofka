package com.microservice.groupone.controller;

import com.microservice.groupone.dto.response.ResponseMessageDto;
import com.microservice.groupone.entity.Client;
import com.microservice.groupone.exception.ResourceNotFoundException;
import com.microservice.groupone.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> createClient(@RequestBody Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(new ResponseMessageDto("Client created"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> updateClient(@PathVariable String id, @RequestBody Client client)
            throws ResourceNotFoundException {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(new ResponseMessageDto("Information updated"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> deleteClientById(@PathVariable String id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(new ResponseMessageDto("Client has been deleted"), HttpStatus.OK);
    }
}
