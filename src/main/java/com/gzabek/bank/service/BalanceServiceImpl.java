package com.gzabek.bank.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzabek.bank.model.dto.AccountBalanceRequest;
import com.gzabek.bank.model.entity.Account;
import com.gzabek.bank.repository.AccountRepository;
import com.gzabek.bank.repository.CustomerRepository;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public BigDecimal getAccountsBalanceForCustomer(Long customerId) {
        return accountRepository.findAccountEntitiesByCustomerId(customerId)
                .orElseThrow(IllegalArgumentException::new)
                .stream()
                .map(i -> i.getBalance())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAccountBalanceForCustomerAndAccount(Long customerId, Long accountId) {
        return accountRepository.findAccountEntityByIdAndCustomerId(accountId, customerId)
                .orElseThrow(IllegalArgumentException::new).getBalance();
    }

    public BigDecimal addMoneyToAccountForCustomer(Long customerId, AccountBalanceRequest request) {

        Optional<Account> accountOptional =
                accountRepository.findAccountEntityByIdAndCustomerId(request.getAccountId(), customerId);
        Account account = accountOptional.orElseThrow(IllegalArgumentException::new);
        BigDecimal newBalance = account.getBalance().add(request.getAmount());
        account.setBalance(newBalance);

        return accountRepository.save(account).getBalance();

    }
}
