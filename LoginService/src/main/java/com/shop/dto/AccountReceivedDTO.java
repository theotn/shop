package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountReceivedDTO {


    @Pattern(regexp = "[A-Z][a-z]+",message = "{account.name.pattern}")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]+",message = "{account.name.pattern}")
    private String lastName;

    @Pattern(regexp="[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)?@([a-zA-Z]+)\\.([a-zA-Z]{2,7})",message = "{account.email.invalid}")
    private String email;

    @Pattern(regexp="((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8}$)",message = "{account.password.invalid}")
    private String password;

    private LocalDate dob;
}
