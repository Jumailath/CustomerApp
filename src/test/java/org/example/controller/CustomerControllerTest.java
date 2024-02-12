package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.example.domain.Customer;
import org.example.exception.CustomerNotFoundException;
import org.example.service.CustomerService;
import org.example.util.TestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.nio.charset.Charset;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    private static final String CREATE_CUSTOMER_URL = "/v1/customers";
    private static final String GET_CUSTOMER_URL = "/v1/customers/1";

    private static final String IMPORT_CUSTOMERS_URL = "/v1/customers/import";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
    }


    @Test
    public void createCustomerSuccessfully() throws Exception {

        Customer customer = TestUtil.getCustomer(1L);
        String customerString  = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post(CREATE_CUSTOMER_URL)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(customerString))
                .andExpect(status().isCreated());

        verify(customerService).createCustomer(customer);
    }

    @Test
    public void getCustomerReturnsCustomer() throws Exception {

        Customer customer = TestUtil.getCustomer(1L);
        String customerString  = objectMapper.writeValueAsString(customer);

        when(customerService.getCustomer(1L)).thenReturn(customer);

        mockMvc.perform(get(GET_CUSTOMER_URL)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(customerString));

        verify(customerService).getCustomer(1L);
    }

    @Test
    public void getCustomerThrowsException() throws Exception {

        when(customerService.getCustomer(1L)).thenThrow(new CustomerNotFoundException("customer 1 not found"));

        mockMvc.perform(get(GET_CUSTOMER_URL)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        verify(customerService).getCustomer(1L);
    }

    @Test
    public void importCustomersSuccessfully() throws Exception {

        mockMvc.perform(post(IMPORT_CUSTOMERS_URL))
                .andExpect(status().isCreated());

        verify(customerService).importCustomers();
    }

    @Test
    public void importCustomersThrowsError() throws Exception {

        doThrow(new RuntimeException("error")).when(customerService).importCustomers();
        mockMvc.perform(post(IMPORT_CUSTOMERS_URL))
                .andExpect(status().is5xxServerError());

        verify(customerService).importCustomers();
    }

    private String getResourceAsString(String resourcePath) {

        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            assert inputStream != null;
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Throwable exe) {
                    throw new RuntimeException(exe);
                }
            }

        }
    }

}