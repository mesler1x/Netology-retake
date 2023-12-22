package ru.netology.roman_mesler.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.roman_mesler.OperationHistoryApiApplicationTest;
import ru.netology.roman_mesler.domain.operation.Currency;
import ru.netology.roman_mesler.domain.operation.Operation;
import ru.netology.roman_mesler.domain.operation.OperationCreditType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatementServiceTest extends OperationHistoryApiApplicationTest {

    @Autowired
    StatementService statementService;

    @BeforeEach
    public void resetStorage() {
        statementService.initStorage();
    }

    @Test
    @Order(1)
    public void getOperations(){
        String operations = statementService.getStringStorage();
        assertEquals("{1=[Operations{operationCreditType=DEBIT, sum=1000.0, currency=RUB, merchant=Starbucks'}, Operations{operationCreditType=DEBIT, sum=200.0, currency=RUB, merchant=YandexTaxi'}], 2=[Operations{operationCreditType=DEBIT, sum=500.0, currency=RUB, merchant=Cinema'}, Operations{operationCreditType=DEBIT, sum=120.0, currency=RUB, merchant=Bakery'}]}", operations);
    }

    @Test
    @Order(2)
    public void getCustomerOperations(){
        int customerId = 2;
        String operations = statementService.getOperationsFromCustomer(customerId).toString();
        assertEquals("[Operations{operationCreditType=DEBIT, sum=500.0, currency=RUB, merchant=Cinema'}," +
                " Operations{operationCreditType=DEBIT, sum=120.0, currency=RUB, merchant=Bakery'}]", operations);
    }

    @Test
    @Order(3)
    public void removeOperationTest(){
        statementService.removeOperationsOnCustomerId(1,1);
        assertEquals("{1=[Operations{operationCreditType=DEBIT, sum=200.0, currency=RUB, merchant=YandexTaxi'}], 2=[Operations{operationCreditType=DEBIT, sum=500.0, currency=RUB, merchant=Cinema'}, Operations{operationCreditType=DEBIT, sum=120.0, currency=RUB, merchant=Bakery'}]}", statementService.getStringStorage());
    }

    @Test
    @Order(4)
    public void saveOperationsTest(){
        Operation operation = new Operation(OperationCreditType.CREDIT, 60, Currency.USD, "Amazon", 2, 1);
        statementService.saveOperation(operation);
        String operations = statementService.getStringStorageOnId(2);
        assertEquals("[Operations{operationCreditType=DEBIT, sum=500.0, currency=RUB, merchant=Cinema'}, Operations{operationCreditType=DEBIT, sum=120.0, currency=RUB, merchant=Bakery'}, Operations{operationCreditType=CREDIT, sum=60.0, currency=USD, merchant=Amazon'}]", operations);
    }
}