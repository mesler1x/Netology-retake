package ru.netology.roman_mesler.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomersGetResponse {
    private final List<CustomerDTO> Customers;
}
