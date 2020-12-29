package org.haulmont.fedoseew.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "BANKS")
@Data
public class Bank extends AbstractModelClass {

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Credit creditInBank;

    public Bank(Client client, Credit creditInBank) {
        this.client = client;
        this.creditInBank = creditInBank;
    }

    public Bank() {}

    @Override
    public String toString() {
        return "Bank: " + this.client + " " + this.creditInBank;
    }

}