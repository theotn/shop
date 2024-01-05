package com.shop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountSentDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
}
