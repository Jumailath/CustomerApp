package org.example.service;

import org.example.domain.Customer;
import org.example.entity.CustomerEntity;
import org.example.repository.CustomerRepository;
import org.example.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.example.util.TestUtil.getCustomer;
import static org.example.util.TestUtil.getCustomerEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final long CUSTOMER_REF = 1L;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CSVFileHandler csvFileHandler;
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerService = new CustomerService(customerRepository, csvFileHandler);
    }

    @Test
    void createCustomerSuccessfully() {
        Customer customer = getCustomer(CUSTOMER_REF);
        CustomerEntity customerEntity = getCustomerEntity(CUSTOMER_REF);
        customerService.createCustomer(customer);

        verify(customerRepository).save(customerEntity);
    }

    @Test
    void createCustomerThrowsExceptionWhenRepositoryErrors() {
        when(customerRepository.save(any(CustomerEntity.class))).thenThrow(new RuntimeException("error"));
        Customer customer = getCustomer(CUSTOMER_REF);
        CustomerEntity customerEntity = getCustomerEntity(CUSTOMER_REF);

        assertThrows(RuntimeException.class, ()-> customerService.createCustomer(customer));
        verify(customerRepository).save(customerEntity);
    }

    @Test
    void getCustomerReturnsCustomerWithCustomerReference() {
        Customer expectedCustomer = getCustomer(CUSTOMER_REF);
        CustomerEntity customerEntity = getCustomerEntity(CUSTOMER_REF);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));

        assertEquals(expectedCustomer, customerService.getCustomer(CUSTOMER_REF));

        verify(customerRepository).findById(CUSTOMER_REF);
    }

    @Test
    void getCustomerThrowsExceptionWhenRepositoryErrors() {
        when(customerRepository.findById(any())).thenThrow(new RuntimeException("error"));

        assertThrows(RuntimeException.class, ()-> customerService.getCustomer(CUSTOMER_REF));
        verify(customerRepository).findById(CUSTOMER_REF);
    }

}