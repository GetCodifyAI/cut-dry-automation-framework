package com.cutanddry.qa.data.models;

import lombok.Data;

@Data
public class CashAndCarryAppUser {
    private String full_name;
    private String email;
    private String mobile;
    private String card_number_valid;
    private String card_number_invalid;
    private String expiration;
    private String cvv;
    private String street_address;
    private String alt_address;
    private String city;
    private String state;
    private String code;
    private String alt_code;
}
