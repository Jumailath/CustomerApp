package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Customer;
import org.example.exception.CustomerNotFoundException;
import org.example.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import static org.example.service.CustomerMapper.maptoDomain;
import static org.example.service.CustomerMapper.maptoEntity;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CSVFileHandler csvFileHandler;

    public void createCustomer(Customer customer) {
       customerRepository.save(maptoEntity(customer));
    }

    public Customer getCustomer(Long customerRef) {

        return maptoDomain(customerRepository.findById(customerRef)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found with customer reference"+customerRef)));
    }

    public void importCustomers() {
        csvFileHandler.handleCsvFile();
    }
}
