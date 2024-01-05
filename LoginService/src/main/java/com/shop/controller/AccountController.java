package com.shop.controller;

import com.shop.dto.AccountDTO;
import com.shop.entity.Account;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;
import com.shop.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AccountDTO> signUp(@RequestBody AccountDTO accountDTO) throws AuthException {

        AccountDTO account = accountService.signUp(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody AccountDTO accountDTO) throws AuthException, NotFoundException {

        AccountDTO account = accountService.login(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
