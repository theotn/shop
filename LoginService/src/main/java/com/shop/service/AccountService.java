package com.shop.service;

import com.shop.dto.AccountReceivedDTO;
import com.shop.dto.AccountSentDTO;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;

public interface AccountService {

    AccountSentDTO signUp(AccountReceivedDTO accountDTO) throws AuthException;

    AccountSentDTO login(AccountReceivedDTO accountDTO) throws AuthException, NotFoundException;

    AccountSentDTO updateAccount(AccountReceivedDTO accountDTO, Integer accountId) throws AuthException, NotFoundException;
    AccountSentDTO getAccount(Integer accountId) throws NotFoundException;
}
