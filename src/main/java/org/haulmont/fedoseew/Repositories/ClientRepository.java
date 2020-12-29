package org.haulmont.fedoseew.Repositories;

import org.haulmont.fedoseew.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
