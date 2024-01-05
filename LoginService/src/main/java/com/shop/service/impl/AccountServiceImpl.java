package com.shop.service.impl;

import com.shop.dto.AccountDTO;
import com.shop.entity.Account;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO signUp(AccountDTO accountDTO) throws AuthException {

        if(accountRepository.existsByEmail(accountDTO.getEmail())) throw new AuthException("This email is already register!");
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(account);

        return modelMapper.map(account,AccountDTO.class);
    }

    @Override
    public AccountDTO login(AccountDTO accountDTO) throws AuthException, NotFoundException {

        if(accountDTO.getEmail()==null || accountDTO.getPassword()==null
                || accountDTO.getEmail().isEmpty() || accountDTO.getPassword().isEmpty()) throw new AuthException("Please provide credentials!");

        Account account = accountRepository.findByEmail(accountDTO.getEmail());

        if(account==null) throw new NotFoundException("The email provided is incorrect!");

        if(passwordEncoder.matches(accountDTO.getPassword(),account.getPassword())){

            return modelMapper.map(account,AccountDTO.class);
        }
          throw new NotFoundException("The credentials provided are incorrect!");
    }
}
