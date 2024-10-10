package com.microservice.group_two.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AccountReportDto {
    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Client")
    private String client;

    @JsonProperty("Account Number")
    private Integer accountNumber;

    @JsonProperty("Type")
    private String typeAccount;

    @JsonProperty("Initial Balance")
    private BigDecimal initialBalance;

    @JsonProperty("Status")
    private boolean status;

    @JsonProperty("Transaction")
    private BigDecimal transactionAmount;

    @JsonProperty("Available Balance")
    private BigDecimal availableBalance;
}
