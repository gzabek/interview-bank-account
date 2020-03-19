package com.gzabek.bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.gzabek.bank.model.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<List<Account>> findAccountEntitiesByCustomerId(Long customerId);

    Optional<Account> findAccountEntityByIdAndCustomerId(Long id, Long customerId);

}
