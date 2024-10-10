package com.microservice.group_two.repository;

import com.microservice.group_two.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByClient(String client);
}
