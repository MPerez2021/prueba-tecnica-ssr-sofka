package com.microservice.group_two.service.account;

import com.microservice.group_two.entity.Account;

public interface AccountService {

    void createAccount(Account account);

    void updateAccount(Account account);

    Account findAccountById(Integer id);

    void deleteAccountById(Integer id);
}
