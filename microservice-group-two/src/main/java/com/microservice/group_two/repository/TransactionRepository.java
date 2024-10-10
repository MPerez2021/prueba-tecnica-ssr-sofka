package com.microservice.group_two.repository;

import com.microservice.group_two.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccount_AccountNumberAndDateBetweenOrderByDateDesc(Integer accountNumber, LocalDate startDate, LocalDate endDate);
}
