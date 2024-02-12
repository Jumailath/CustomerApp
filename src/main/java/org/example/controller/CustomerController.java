package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.domain.Customer;
import org.example.exception.CustomerNotFoundException;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("/v1/customers")
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "createCustomer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account  created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer)
    {
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "getCustomer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping(value="/{customerRef}" , produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerRef)
    {
        Customer customer = customerService.getCustomer(customerRef);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Operation(summary = "importCustomers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customers imported"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping(value = "/import")
    public ResponseEntity<Void> importCustomers()
    {
        customerService.importCustomers();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public void handleCustomerNotFoundException() {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public void handleException() {
    }
}
