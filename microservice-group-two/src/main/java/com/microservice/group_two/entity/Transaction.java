package com.microservice.group_two.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String transactionType;

    private BigDecimal amount;

    private BigDecimal balance;

    private LocalDate date;

    @ManyToOne
    @JsonBackReference
    private Account account;
}
