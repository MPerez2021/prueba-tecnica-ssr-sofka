package com.microservice.groupone.repository;

import com.microservice.groupone.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
