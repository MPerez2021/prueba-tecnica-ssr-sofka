package com.microservice.group_two.service.transaction;

import com.microservice.group_two.dto.request.CreateTransactionDto;
import com.microservice.group_two.entity.Transaction;

public interface TransactionService {
    void createTransaction(CreateTransactionDto createTransactionDto) throws Exception;

    void updateTransaction(Transaction transaction);

    Transaction findTransactionById(String id);

    void deleteTransactionById(String id);
}
