package ru.netology.roman_mesler.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CustomerController customerController;

    @Test
    public void testGetCustomer() throws Exception {
        int customerId = 1;
        String url = "/api/customers/" + customerId;
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customerId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Boot"));
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].id").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[0].name").value("Spring"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers[1].name").value("Boot"));
    }

    @Test
    public void testAddCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/")
                        .param("id", "2")
                        .param("name", "New Customer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Customer"));
    }
}
