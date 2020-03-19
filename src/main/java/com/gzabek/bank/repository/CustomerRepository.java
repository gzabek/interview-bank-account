package com.gzabek.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.gzabek.bank.model.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
