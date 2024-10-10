package com.microservice.group_two.service.transaction;

import com.microservice.group_two.dto.request.CreateTransactionDto;
import com.microservice.group_two.entity.Account;
import com.microservice.group_two.entity.Transaction;
import com.microservice.group_two.exception.InsufficientBalanceException;
import com.microservice.group_two.exception.TransactionMinAmountException;
import com.microservice.group_two.repository.TransactionRepository;
import com.microservice.group_two.service.account.AccountServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountServiceImpl accountService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "transaction-topic";
    @Override
    public void createTransaction(CreateTransactionDto createTransactionDto) throws Exception {
        validateTransactionAmount(createTransactionDto.getAmount());

        Account account = accountService.findAccountById(createTransactionDto.getAccountNumber());
        BigDecimal newBalance = account.getInitialBalance().add(createTransactionDto.getAmount());

        validateBalance(newBalance);

        saveTransactionInDatabase(createTransactionDto, account, newBalance);

        accountService.updateInitialBalance(account, newBalance);
        sendTransactionMessage(createTransactionDto.getAmount());
    }
    private void validateTransactionAmount(BigDecimal amount) throws TransactionMinAmountException {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new TransactionMinAmountException();
        }
    }

    private void validateBalance(BigDecimal newBalance) throws InsufficientBalanceException {
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Balance not available");
        }
    }
    private void saveTransactionInDatabase(CreateTransactionDto createTransactionDto, Account account, BigDecimal newBalance) {
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDate.now());
        transaction.setTransactionType(createTransactionDto.getTransactionType());
        transaction.setAmount(createTransactionDto.getAmount());
        transaction.setBalance(newBalance);
        transaction.setAccount(account);
        transactionRepository.save(transaction);
    }

    private void sendTransactionMessage(BigDecimal amount) {
        String message = "Transaction made on " + LocalDate.now() + " with the amount of $" + amount;
        kafkaTemplate.send(TOPIC, message);
    }
    @Override
    public void updateTransaction(Transaction transaction) {
        Transaction transactionFound = findTransactionById(transaction.getId());

        transactionFound = Transaction.builder()
                .id(transactionFound.getId())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .account(transactionFound.getAccount())
                .balance(transaction.getBalance())
                .build();

        transactionRepository.save(transactionFound);
    }

    @Override
    public Transaction findTransactionById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    @Override
    public void deleteTransactionById(String id) {
        transactionRepository.deleteById(id);
    }
}
