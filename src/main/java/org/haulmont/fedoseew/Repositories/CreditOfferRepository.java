package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, Long> {
}
