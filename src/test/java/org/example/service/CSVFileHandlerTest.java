package org.example.service;

import com.fasterxml.jackson.databind.ObjectReader;
import org.example.client.CustomerClient;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.example.util.TestUtil.getCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CSVFileHandlerTest {
    private CSVFileHandler csvFileHandler;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ObjectReader objectReader;

    @BeforeEach
    void setup() {
        csvFileHandler = new CSVFileHandler("customer_data", customerRepository, objectReader);
    }

    @Test
    void createCustomerSuccessfully() {
        csvFileHandler.handleCsvFile();
    }

}