package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Long> {
}
