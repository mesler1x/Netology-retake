package ru.netology.roman_mesler.domain.operation;

import java.time.LocalDateTime;

public abstract class BaseOperation {
    private int id;
    private LocalDateTime time;

    public abstract void printToConsole();



    public int getOperationId() {
        return id;
    }

    public void setOperationId(int operationId) {
        this.id = operationId;
    }

    @Override
    public String toString() {
        return "BaseOperation{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}