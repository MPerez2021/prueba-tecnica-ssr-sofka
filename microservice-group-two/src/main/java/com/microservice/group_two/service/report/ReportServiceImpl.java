package com.microservice.group_two.service.report;

import com.microservice.group_two.dto.response.AccountReportDto;
import com.microservice.group_two.entity.Account;
import com.microservice.group_two.entity.Transaction;
import com.microservice.group_two.repository.AccountRepository;
import com.microservice.group_two.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public List<AccountReportDto> getAccountReportByClientAndDateRange(String clientName, LocalDate startDate, LocalDate endDate) {
        Account account = accountRepository.findByClient(clientName);
        List<Transaction> transactionList = transactionRepository.findByAccount_AccountNumberAndDateBetweenOrderByDateDesc(account.getAccountNumber(), startDate, endDate);
        List<AccountReportDto> accountReportDtoList = new ArrayList<>();

        transactionList.forEach(transaction -> {
            AccountReportDto accountReportDto = AccountReportDto.builder()
                    .date(transaction.getDate())
                    .client(account.getClient())
                    .accountNumber(account.getAccountNumber())
                    .typeAccount(account.getTypeAccount())
                    .initialBalance(transaction.getBalance().subtract(transaction.getAmount()))
                    .status(account.isStatus())
                    .transactionAmount(transaction.getAmount())
                    .availableBalance(transaction.getBalance())
                    .build();
            accountReportDtoList.add(accountReportDto);
        });
        return accountReportDtoList;
    }

}
