package ru.netology.roman_mesler.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.netology.roman_mesler.domain.operation.Currency;
import ru.netology.roman_mesler.domain.operation.OperationCreditType;

@Data
@AllArgsConstructor
public class OperationsDTO {
    private final int customerId;
    private final double sum;
    private final Currency currency;
    private final String merchant;
    private final OperationCreditType operationCreditType;
}
