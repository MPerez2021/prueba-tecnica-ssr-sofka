package com.microservice.group_two.service.account;

import com.microservice.group_two.entity.Account;
import com.microservice.group_two.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }

    @Override
    public void updateAccount(Account account) {
        Account accountFound = findAccountById(account.getAccountNumber());

        accountFound = Account.builder()
                .accountNumber(accountFound.getAccountNumber())
                .typeAccount(account.getTypeAccount())
                .initialBalance(account.getInitialBalance())
                .status(account.isStatus())
                .client(account.getClient())
                .transactions(accountFound.getTransactions())
                .build();
        accountRepository.save(accountFound);
    }

    @Override
    public void deleteAccountById(Integer id) {
        accountRepository.deleteById(id);
    }

    public void updateInitialBalance(Account account, BigDecimal newBalance) {
        account.setInitialBalance(newBalance);
        accountRepository.save(account);
    }
}
