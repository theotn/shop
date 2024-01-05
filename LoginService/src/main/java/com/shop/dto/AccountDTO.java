package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
}
