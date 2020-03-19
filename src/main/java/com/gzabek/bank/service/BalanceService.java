package com.gzabek.bank.service;

import java.math.BigDecimal;

import com.gzabek.bank.model.dto.AccountBalanceRequest;

public interface BalanceService {

    /**
     * Returns a total balance for all customer accounts with passed customer id.
     * @param customerId id of user
     * @return total balance for all customer accounts
     * @throws IllegalArgumentException if there is no such customer
     */
    BigDecimal getAccountsBalanceForCustomer(Long customerId) throws IllegalArgumentException;

    /**
     * Returns a balance for customer account with passed customer id and account id.
     * @param customerId id of customer
     * @param accountId id of account
     * @return balance for customer account
     * @throws IllegalArgumentException if there is no such customer or account
     */
    BigDecimal getAccountBalanceForCustomerAndAccount(Long customerId, Long accountId) throws IllegalArgumentException;

    /**
     * Returns a new balance for customer account with passed customer id and account id and new amount.
     * @param customerId id of customer
     * @param request id of account with additional funds amount
     * @return balance for customer account
     * @throws IllegalArgumentException if there is no such customer or account
     */
    BigDecimal addMoneyToAccountForCustomer(Long customerId, AccountBalanceRequest request) throws IllegalArgumentException;

}
