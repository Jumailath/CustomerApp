package org.example.service;

import org.example.domain.Customer;
import org.example.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity maptoEntity(Customer customer) {
        return CustomerEntity.builder()
                .customerName(customer.getCustomerName())
                .customerRef(Long.valueOf(customer.getCustomerReference()))
                .addressLine1(customer.getAddressLine1())
                .addressLine2(customer.getAddressLine2())
                .town(customer.getTown())
                .county(customer.getCounty())
                .country(customer.getCountry())
                .postcode(customer.getPostcode())
                .build();
    }

    public static Customer maptoDomain(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerName(customerEntity.getCustomerName())
                .customerReference(String.valueOf(customerEntity.getCustomerRef()))
                .addressLine1(customerEntity.getAddressLine1())
                .addressLine2(customerEntity.getAddressLine2())
                .town(customerEntity.getTown())
                .county(customerEntity.getCounty())
                .country(customerEntity.getCountry())
                .postcode(customerEntity.getPostcode())
                .build();
    }
}
