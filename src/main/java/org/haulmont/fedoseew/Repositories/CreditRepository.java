package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query("select credit from Credit credit where credit.creditLimit >= :creditAmount")
    List<Credit> findCreditsByAmount(
            @Param("creditAmount") Long creditAmount);
}
