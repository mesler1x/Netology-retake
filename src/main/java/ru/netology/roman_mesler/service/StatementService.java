package ru.netology.roman_mesler.service;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.netology.roman_mesler.domain.operation.Currency;
import ru.netology.roman_mesler.domain.operation.Operation;
import ru.netology.roman_mesler.domain.operation.OperationCreditType;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
@AllArgsConstructor
public class StatementService {

    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    @PostConstruct
    public void initStorage() {
        List<Operation> firstInitialList = new ArrayList<>();
        firstInitialList.add(new Operation(OperationCreditType.DEBIT, 1000, Currency.RUB, "Starbucks", 1, 1));
        firstInitialList.add(new Operation(OperationCreditType.DEBIT, 200, Currency.RUB, "YandexTaxi", 1, 2));
        storage.put(1,firstInitialList);
        List<Operation> secondInitialList = new ArrayList<>();
        secondInitialList.add(new Operation(OperationCreditType.DEBIT, 500, Currency.RUB, "Cinema", 2, 1));
        secondInitialList.add(new Operation(OperationCreditType.DEBIT, 120, Currency.RUB, "Bakery", 2,2));
        storage.put(2,secondInitialList);
    }

    public String getStringStorage(){
        return storage.toString();
    }
    public String getStringStorageOnId(int customerId){
        return storage.get(customerId).toString();
    }

    public void saveOperation(Operation operation){
        List<Operation> operations = storage.get(operation.getCustomerId());
        if (operations == null){
            List<Operation> customerOperations = new ArrayList<>();
            customerOperations.add(operation);
            storage.put(operation.getCustomerId(), customerOperations);
        } else {
            operations.add(operation);
        }
    }

    public List<Operation> getOperationsFromCustomer(int customerId) {
        return storage.get(customerId);
    }
    public void removeOperationsOnCustomerId(int customerId, int operationId){
        List<Operation> allUserOperationsById = storage.get(customerId);
        for(Operation operation: allUserOperationsById){
            if (operation.getOperationId() == operationId){
                storage.get(customerId).remove(operationId - 1);
            }
        }
    }
}
