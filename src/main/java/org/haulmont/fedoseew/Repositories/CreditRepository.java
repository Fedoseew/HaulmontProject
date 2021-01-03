package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

}
