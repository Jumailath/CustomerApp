package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.example.service.CustomerMapper.maptoEntity;

@Component
@Slf4j
public class CSVFileHandler {
    private ObjectReader objectReader;
    private CustomerRepository customerRepository;
    private String fileName;

    public CSVFileHandler(@Value("${customer.data.file.name:customer_data}") String fileName, CustomerRepository customerRepository, ObjectReader objectReader) {
        this.fileName = fileName;
        this.customerRepository = customerRepository;
        this.objectReader = objectReader;
    }
    public void handleCsvFile() {
        Stream<String> lines = null;
        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                    .getResource(fileName+".csv")).toURI());
            lines = Files.lines(path);
            lines.map(this::mapToCustomer)
                    .filter(Optional::isPresent)
                    .forEach(c-> save(c.get()));

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }finally {
            lines.close();
        }
    }

    public void save(Customer customer) {
        customerRepository.save(maptoEntity(customer));
    }
    public Optional<Customer> mapToCustomer(String line) {
        try {
            Customer customer = objectReader.readValue(line);
            return Optional.of(customer);
        }catch (JsonProcessingException ex) {
            log.error("customer not imported",ex);
            return Optional.empty();
        }
    }
}
