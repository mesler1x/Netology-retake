
package ru.netology.roman_mesler.domain.operation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.netology.roman_mesler.domain.ConsolePrintable;

import java.util.Objects;


@Setter
@Getter
@AllArgsConstructor
public class Operation extends BaseOperation implements ConsolePrintable {

    private OperationCreditType operationCreditType;
    private double sum;
    private Currency currency;
    private String merchant;
    private int customerId;
    private int operationId;

    public Operation(){

    }

    public void printToConsole(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Operations{" +
                "operationCreditType=" + operationCreditType.name() +
                ", sum=" + sum +
                ", currency=" + currency.name() +
                ", merchant=" + merchant + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation that = (Operation) o;

        if (Double.compare(sum, that.sum) != 0) return false;
        if (customerId != that.customerId) return false;
        if (operationCreditType != that.operationCreditType) return false;
        if (currency != that.currency) return false;
        return Objects.equals(merchant, that.merchant);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = operationCreditType != null ? operationCreditType.hashCode() : 0;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (merchant != null ? merchant.hashCode() : 0);
        result = 31 * result + customerId;
        return result;
    }



}

