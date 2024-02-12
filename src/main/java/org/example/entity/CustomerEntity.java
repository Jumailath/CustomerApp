package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Configurable
@Table(name="customer")
public class CustomerEntity {

    @Column(name = "id")
    @Id
    private Long customerRef;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;
}
