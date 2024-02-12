package org.example.util;

import org.example.domain.Customer;
import org.example.entity.CustomerEntity;

public class TestUtil {

    public static Customer getCustomer(Long customerRef) {
        return getCustomerBuilder(customerRef).build();
    }

    public static Customer.CustomerBuilder getCustomerBuilder(Long customerRef) {
        return Customer.builder()
                .customerReference(String.valueOf(customerRef))
                .customerName("test name")
                .addressLine1("line1")
                .addressLine2("line2")
                .county("county")
                .country("UK")
                .town("town")
                .postcode("CC12ED");
    }

    public static CustomerEntity getCustomerEntity(Long customerRef) {
        return CustomerEntity.builder()
                .customerRef(customerRef)
                .customerName("test name")
                .addressLine1("line1")
                .addressLine2("line2")
                .county("county")
                .country("UK")
                .town("town")
                .postcode("CC12ED").build();
    }
}
