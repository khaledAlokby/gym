package com.wix.gym.repository;

import com.wix.gym.model.Class;
import com.wix.gym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
