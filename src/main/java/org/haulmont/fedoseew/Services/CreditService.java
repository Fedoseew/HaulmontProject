package org.haulmont.fedoseew.Services;

import org.haulmont.fedoseew.Models.Client;
import org.haulmont.fedoseew.Models.Credit;
import org.haulmont.fedoseew.Repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class CreditService {

    @Autowired
    CreditRepository creditRepository;

    public CreditService() { }

    public void deleteById(Long id) {

        creditRepository.delete(id);
        System.out.println("Delete Credit with ID " + id);
    }

    public void delete(Credit credit) {
        System.out.println("Delete Credit: "+ credit.getCreditLimit() + ", " + credit.getCreditProcent());
        creditRepository.delete(credit);

    }

    public List<Credit> findAll() {
        List<Credit> credits = creditRepository.findAll();
        Collections.sort(credits);
        return credits;
    }

    public Credit findCredit(Long creditAmount, Double creditProcent) {
        Credit credit = new Credit(creditAmount, creditProcent);
        return creditRepository.findOne(Example.of(credit));
    }

    public List<Credit> findCreditsByAmount(Long creditAmount) {
       return creditRepository.findCreditsByAmount(creditAmount);
    }

    public void save(Credit credit) { creditRepository.save(credit);
        System.out.println("Save Credit: "+ credit.getCreditLimit() + ", " + credit.getCreditProcent());}
}