package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}

