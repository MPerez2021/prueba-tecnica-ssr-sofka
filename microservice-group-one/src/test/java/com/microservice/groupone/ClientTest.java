package com.microservice.groupone;

import com.microservice.groupone.entity.Client;
import com.microservice.groupone.repository.ClientRepository;
import com.microservice.groupone.service.ClientService;
import com.microservice.groupone.service.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientTest {
    @Mock
    private ClientRepository clientRepository; // Suponiendo que tienes un repositorio para los clientes

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createClient_WhenValidClientDetailsProvided_shouldSaveClient() {
        // Arrange
        Client client = new Client();
        client.setId("12345");
        client.setName("Juan Pérez");
        client.setGender("Masculino");
        client.setAge(30);
        client.setCi("1234567890");
        client.setAddress("Calle Falsa 123");
        client.setPhoneNumber("0987654321");
        client.setPassword("1235");
        client.setStatus(true);

        // Act
        clientRepository.save(client);

        // Assert
        verify(clientRepository,times(1)).save(client);
    }
}
