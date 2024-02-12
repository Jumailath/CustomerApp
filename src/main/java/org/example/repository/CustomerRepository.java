package org.example.repository;

import org.example.entity.CustomerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
