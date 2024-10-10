package com.microservice.group_two.controller;

import com.microservice.group_two.dto.response.ResponseMessageDto;
import com.microservice.group_two.entity.Account;
import com.microservice.group_two.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ResponseMessageDto> createAccount(@RequestBody Account account){
        accountService.createAccount(account);
        return new ResponseEntity<>(new ResponseMessageDto("Account created"), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<ResponseMessageDto> updateAccount(@RequestBody Account account){
        accountService.updateAccount(account);
        return new ResponseEntity<>(new ResponseMessageDto("Information updated"), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable Integer id){
        return ResponseEntity.ok(accountService.findAccountById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDto> deleteAccountById(@PathVariable("id") Integer id){
        accountService.deleteAccountById(id);
        return new ResponseEntity<>(new ResponseMessageDto("Account has been deleted"), HttpStatus.OK);
    }
}
