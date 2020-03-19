package com.gzabek.bank.model.dto;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBalanceRequest {

    private final Long accountId;
    private final BigDecimal amount;

    @JsonCreator
    public AccountBalanceRequest(@JsonProperty("accountId") Long accountId,
            @JsonProperty("amount") BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccountBalanceRequest request = (AccountBalanceRequest) o;
        return Objects.equals(accountId, request.accountId) &&
                Objects.equals(amount, request.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, amount);
    }
}
