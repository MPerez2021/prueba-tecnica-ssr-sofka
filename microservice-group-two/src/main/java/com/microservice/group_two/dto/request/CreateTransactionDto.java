package com.microservice.group_two.dto.request;

import com.microservice.group_two.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
public class CreateTransactionDto {
    private Integer accountNumber;
    private String transactionType;
    private BigDecimal amount;
}
