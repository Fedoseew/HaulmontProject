package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
