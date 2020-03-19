package com.gzabek.bank.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gzabek.bank.model.entity.Account;
import com.gzabek.bank.model.entity.Customer;
import com.gzabek.bank.repository.AccountRepository;
import com.gzabek.bank.repository.CustomerRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public DataLoader(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    public void run(ApplicationArguments args) {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Greg");

        customerRepository.save(customer);

        Account account1 = new Account();
        account1.setId(1L);
        account1.setCustomer(customer);
        account1.setBalance(new BigDecimal(100));

        accountRepository.save(account1);

        Account account2 = new Account();
        account2.setId(2L);
        account2.setCustomer(customer);
        account2.setBalance(new BigDecimal(1000));

        accountRepository.save(account2);
    }
}
