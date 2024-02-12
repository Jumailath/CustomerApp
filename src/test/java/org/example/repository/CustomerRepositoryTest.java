package org.example.repository;

import org.example.domain.Customer;
import org.example.entity.CustomerEntity;
import org.example.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@DataJpaTest
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeAll
    public static void setup() {
    }
    @Test
    public void shouldReturnOptionalCustomerOnSaveAndFindById() {
        CustomerEntity expectedCustomer = TestUtil.getCustomerEntity(1L);

        customerRepository.save(expectedCustomer);

        assertEquals(TestUtil.getCustomer(1L), customerRepository.findById(1L).get());
    }

    @Test
    public void shouldReturnOptionalEmptyFindById() {

        assertEquals(Optional.empty(), customerRepository.findById(2L));
    }
}
