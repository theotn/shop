package com.shop.service.impl;

import com.shop.dto.AccountReceivedDTO;
import com.shop.dto.AccountSentDTO;
import com.shop.entity.Account;
import com.shop.exception.AuthException;
import com.shop.exception.NotFoundException;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;
import jakarta.persistence.Entity;
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
    public AccountSentDTO signUp(AccountReceivedDTO accountDTO) throws AuthException {

        if(accountRepository.existsByEmail(accountDTO.getEmail())) throw new AuthException("This email is already register!");
        Account account = modelMapper.map(accountDTO,Account.class);
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountRepository.save(account);

        return modelMapper.map(account,AccountSentDTO.class);
    }

    @Override
    public AccountSentDTO login(AccountReceivedDTO accountDTO) throws AuthException, NotFoundException {


        Account account = accountRepository.findByEmail(accountDTO.getEmail());

        if(account==null) throw new NotFoundException("The email provided is incorrect!");

        if(passwordEncoder.matches(accountDTO.getPassword(),account.getPassword())){

            return modelMapper.map(account,AccountSentDTO.class);
        }
          throw new NotFoundException("The credentials provided are incorrect!");
    }

    @Override
    public AccountSentDTO updateAccount(AccountReceivedDTO accountDTO, Integer accountId) throws AuthException, NotFoundException {

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account account = optionalAccount.orElseThrow(()-> new NotFoundException("This account doesn't exist!"));

        if(accountDTO.getEmail()!=null){
            if(accountRepository.existsByEmail(accountDTO.getEmail())) throw new AuthException("This email is already register!");
            account.setEmail(accountDTO.getEmail());
        }
        if(accountDTO.getFirstName()!=null && !accountDTO.getFirstName().isEmpty()){
            account.setFirstName(accountDTO.getFirstName());
        }
        if(accountDTO.getLastName()!=null && !accountDTO.getLastName().isEmpty()){
            account.setLastName(accountDTO.getLastName());
        }
        if(accountDTO.getDob()!=null){
            account.setDob(accountDTO.getDob());
        }
        if(accountDTO.getPassword()!=null && !accountDTO.getPassword().isEmpty()){
            account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        }
        return modelMapper.map(account,AccountSentDTO.class);
    }

    @Override
    public AccountSentDTO getAccount(Integer accountId) throws NotFoundException {

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        Account account = optionalAccount.orElseThrow(()-> new NotFoundException("This account doesn't exist!"));

        return modelMapper.map(account,AccountSentDTO.class);
    }
}
