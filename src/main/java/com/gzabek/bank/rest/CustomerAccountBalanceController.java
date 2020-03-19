package com.gzabek.bank.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gzabek.bank.model.dto.AccountBalanceRequest;
import com.gzabek.bank.service.BalanceService;

/**
 * Customer Account Balance Rest API
 */
@RestController
@RequestMapping("/bank/customer")
public class CustomerAccountBalanceController {

    private final BalanceService balanceService;

    public CustomerAccountBalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Get total customer balance from all accounts.
     *
     * @param id customer ID
     * @return balance
     */
    @GetMapping(value = "/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTotalBalance(@PathVariable Long id) {

        return balanceService.getAccountsBalanceForCustomer(id);
    }

    /**
     * Get customer balance from given account.
     *
     * @param id customer ID
     * @param number account No
     * @return balance
     */
    @GetMapping(value = "/{id}/balance/account/{number}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTotalBalanceForAccount(@PathVariable Long id, @PathVariable Long number) {

        return balanceService.getAccountBalanceForCustomerAndAccount(id, number);
    }

    /**
     * Add funds to customer account.
     *
     * @param id customer ID
     * @param request account number with new ammount
     * @return balance
     */
    @PostMapping(value = "/{id}/balance/add", consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal addFundsToAccount(@PathVariable Long id, @RequestBody AccountBalanceRequest request) {
        return balanceService.addMoneyToAccountForCustomer(id, request);
    }

}
