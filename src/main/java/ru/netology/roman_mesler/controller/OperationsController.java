package ru.netology.roman_mesler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.netology.roman_mesler.domain.operation.Operation;
import ru.netology.roman_mesler.controller.dto.OperationsDTO;
import ru.netology.roman_mesler.controller.dto.OperationsGetResponse;
import ru.netology.roman_mesler.service.AsyncInputOperationService;
import ru.netology.roman_mesler.service.StatementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/operations/")
public class OperationsController {
    private final StatementService statementService;

    private final AsyncInputOperationService asyncInputOperationService;

    @Autowired
    public OperationsController(StatementService statementService, AsyncInputOperationService asyncInputOperationService) {
        this.statementService = statementService;
        this.asyncInputOperationService = asyncInputOperationService;
    }

    @GetMapping("{id}")
    public OperationsGetResponse checkOperationsByCustomerId(@PathVariable("id") int id){
        List<Operation> operations = statementService.getOperationsFromCustomer(id);
        List<OperationsDTO> operationDTOS = operations.stream()
                .map(operation ->
                        new OperationsDTO(operation.getOperationId(),operation.getSum(), operation.getCurrency(), operation.getMerchant(), operation.getOperationCreditType())).collect(Collectors.toList());
        return new OperationsGetResponse(operationDTOS);
    }

    @PostMapping()
    public OperationsDTO addOperation(@RequestBody Operation operation){
        asyncInputOperationService.addOperation(operation);
        return new OperationsDTO(operation.getOperationId(),operation.getSum(), operation.getCurrency(), operation.getMerchant(), operation.getOperationCreditType());
    }

    @DeleteMapping("/delete/{customerId}/{operationId}")
    public void deleteOperation(@PathVariable("customerId") int customerId, @PathVariable("operationId") int operationId){
        statementService.removeOperationsOnCustomerId(customerId, operationId);
    }
}
