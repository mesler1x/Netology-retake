package ru.netology.roman_mesler.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.netology.roman_mesler.configuration.OperationProcessingProperties;
import ru.netology.roman_mesler.domain.operation.Operation;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@RequiredArgsConstructor
public class AsyncInputOperationService {

    @Getter
    private final Queue<Operation> operations = new LinkedList<>();
    private final StatementService statementService;
    private final OperationProcessingProperties operationProperties;

    @PostConstruct
    public void init(){
        this.startProcessing();
    }

    public void addOperation(Operation operation) {
        System.out.println("Operation added for processing " + operation);
        operations.offer(operation);
    }

    public void startProcessing() {
        Thread t = new Thread(this::processQueue);
        t.start();
    }

    private void processQueue() {
        while (true) {
            Operation operation = operations.poll();
            if (operation == null) {
                try {
                    System.out.println("No operations");
                    Thread.sleep(operationProperties.getSleepMilliSeconds());
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println("Processing operation " + operation);
                processOperation(operation);
            }
        }
    }

    private void processOperation(Operation operation) {
        statementService.saveOperation(operation);
    }
}
