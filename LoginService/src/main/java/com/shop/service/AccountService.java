package com.shop.service;

import com.shop.dto.AccountDTO;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;

public interface AccountService {

    AccountDTO signUp(AccountDTO accountDTO) throws AuthException;

    AccountDTO login(AccountDTO accountDTO) throws AuthException, NotFoundException;
}
