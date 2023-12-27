package ru.netology.roman_mesler.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.netology.roman_mesler.domain.Customer;

import javax.annotation.PostConstruct;
import java.util.List;

@Getter
@Service
public class CustomerService {

    private final List<Customer> storage;

    @PostConstruct
    public void initStorage() {
        storage.add(new Customer(0, "Spring"));
        storage.add(new Customer(1, "Boot"));
    }

    public CustomerService(List<Customer> storage) {
        this.storage = storage;
    }

    public void addCustomer(int id, String name) {
        Customer customer = new Customer(id, name);
        storage.add(customer);
    }

    public Customer getCustomer(int customerId) {
        for (Customer customer: storage){
            if (customer.getId() == customerId){
                return customer;
            }
        }
        return null;
    }

}