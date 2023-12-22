package ru.netology.roman_mesler.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.roman_mesler.OperationHistoryApiApplicationTest;
import ru.netology.roman_mesler.domain.operation.Currency;
import ru.netology.roman_mesler.domain.operation.OperationCreditType;
import ru.netology.roman_mesler.domain.operation.Operation;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;

    private final Operation operation = new Operation(OperationCreditType.DEBIT, 12, Currency.RUB, "Tax", 1, 1);

    @Test
    @Order(1)
    public void testStartProcessing_ShouldStartThreadForProcessingQueue() {
        asyncInputOperationService.startProcessing();
        assertTrue(asyncInputOperationService.getOperations().isEmpty());
    }

    @Test
    @Order(2)
    public void getOperationsTest(){
        asyncInputOperationService.addOperation(operation);
        Queue<Operation> operations = asyncInputOperationService.getOperations();
        assertEquals("[Operations{operationCreditType=DEBIT, sum=12.0, currency=RUB, merchant=Tax'}]", operations.toString());
    }

    @Test
    @Order(3)
    public void addOperationTest(){
        int beforeAdd = asyncInputOperationService.getOperations().size();
        asyncInputOperationService.addOperation(operation);
        int AfterAdd = asyncInputOperationService.getOperations().size();
        assertEquals(AfterAdd-beforeAdd, 1);
    }
}
