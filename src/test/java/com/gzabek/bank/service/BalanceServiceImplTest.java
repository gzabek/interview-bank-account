package com.gzabek.bank.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.gzabek.bank.config.ServicesContext;
import com.gzabek.bank.model.dto.AccountBalanceRequest;
import com.gzabek.bank.model.entity.Account;
import com.gzabek.bank.model.entity.Customer;
import com.gzabek.bank.repository.AccountRepository;
import com.gzabek.bank.repository.CustomerRepository;

@SpringBootTest
@ContextConfiguration(classes = ServicesContext.class)
@Transactional
class BalanceServiceImplTest {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountRepository accountRepository;

    @Autowired
    public BalanceService balanceService;

    @BeforeEach
    void setUp() {
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

    @Test
    void getAccountsBalanceForCustomer() {

        BigDecimal balance = balanceService.getAccountsBalanceForCustomer(1L);

        assertThat(balance).isEqualTo(new BigDecimal(1100));

    }

    @Test
    void getAccountBalanceForCustomerAndAccount() {

        BigDecimal balance = balanceService.getAccountBalanceForCustomerAndAccount(1L, 1L);

        assertThat(balance).isEqualTo(new BigDecimal(100));
    }

    @Test
    void addMoneyToAccountForCustomer() {
        AccountBalanceRequest request = new AccountBalanceRequest(2L, new BigDecimal(10000));
        BigDecimal balance = balanceService.addMoneyToAccountForCustomer(1L, request);

        assertThat(balance).isEqualTo(new BigDecimal(11000));
    }

    @Test
    public void shouldThrowForNotExistingCustomer() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getAccountsBalanceForCustomer(10L);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getAccountBalanceForCustomerAndAccount(10L, 1L);
        });

        AccountBalanceRequest request = new AccountBalanceRequest(1L, new BigDecimal(1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.addMoneyToAccountForCustomer(10L, request);
        });

    }

    @Test
    public void shouldThrowForNotExistingAccount() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getAccountBalanceForCustomerAndAccount(1L, 10L);
        });

        AccountBalanceRequest request = new AccountBalanceRequest(10L, new BigDecimal(1));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.addMoneyToAccountForCustomer(1L, request);
        });

    }

    @Test
    public void shouldNotReturnAccountBalanceForNotCustomerAccount() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Bob");

        customerRepository.save(customer);

        Account account = new Account();
        account.setId(3L);
        account.setCustomer(customer);
        account.setBalance(new BigDecimal(10));

        accountRepository.save(account);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            balanceService.getAccountBalanceForCustomerAndAccount(1L, 3L);
        });
    }
}