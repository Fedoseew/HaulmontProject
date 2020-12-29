package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

}
