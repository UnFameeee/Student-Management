package com.example.springbootcloud.converter;

import com.example.springbootcloud.entity.Account;
import com.example.springbootcloud.model.dto.AccountDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {
    public Account toEntity(AccountDTO dto){
        Account account = new Account();
        account.setAccount_id(dto.getAccount_id());
        account.setUsername(dto.getUsername());
        account.setPassword(dto.getPassword());
        account.setRole(dto.getRole());
        return account;
    }

    public AccountDTO toDTO(Account entity){
        AccountDTO dto = new AccountDTO();
        dto.setAccount_id(entity.getAccount_id());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }
}
