package org.haulmont.fedoseew.Models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CREDITS")
@Data
public class Credit extends AbstractModelClass implements Comparable<Credit> {

    @NotNull
    @Column(name = "CREDIT_LIMIT")
    private Long creditLimit;

    @NotNull
    @Column(name = "CREDIT_PROCENT")
    private Double creditProcent;


    public Credit() {
    }

    public Credit(Long creditLimit, Double creditProcent) {
        this.creditLimit = creditLimit;
        this.creditProcent = creditProcent;
    }

    @Override
    public String toString() {
        return this.creditLimit + " руб., " + this.creditProcent + " %";
    }

    @Override
    public int compareTo(Credit o) {
        return o.getCreditLimit().compareTo(this.getCreditLimit());
    }
}
