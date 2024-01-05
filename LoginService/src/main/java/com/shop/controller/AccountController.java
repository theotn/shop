package com.shop.controller;

import com.shop.dto.AccountReceivedDTO;
import com.shop.dto.AccountSentDTO;
import com.shop.entity.Account;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;
import com.shop.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AccountSentDTO> signUp(@Valid @RequestBody AccountReceivedDTO accountDTO) throws AuthException {

        AccountSentDTO account = accountService.signUp(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AccountSentDTO> login(@Valid @RequestBody AccountReceivedDTO accountDTO) throws AuthException, NotFoundException {

        AccountSentDTO account = accountService.login(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @GetMapping("/account")
    public ResponseEntity<AccountSentDTO> getAccount(@RequestParam("account") Integer accountId) throws NotFoundException {

        AccountSentDTO account = accountService.getAccount(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<AccountSentDTO> update(@Valid @RequestBody AccountReceivedDTO accountDTO, @RequestParam("account") Integer accountId) throws AuthException, NotFoundException {

        AccountSentDTO account = accountService.updateAccount(accountDTO,accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
