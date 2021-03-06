package org.haulmont.fedoseew.Services;

import org.haulmont.fedoseew.Models.CreditOffer;
import org.haulmont.fedoseew.Models.PaymentSchedule;
import org.haulmont.fedoseew.Repositories.CreditOfferRepository;
import org.haulmont.fedoseew.Repositories.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditOfferService {
    @Autowired
    CreditOfferRepository creditOfferRepository;

    public CreditOfferService() {
    }

    public void deleteById(Long id) {
        creditOfferRepository.delete(id);
    }

    public void delete(CreditOffer creditOffer) {
        creditOfferRepository.delete(creditOffer);
    }

    public List<CreditOffer> findAll() {
        return creditOfferRepository.findAll();
    }

    public List<CreditOffer> findAllOffersForClient(long bankID) {
        return creditOfferRepository.findAllOffersForClient(bankID);
    }

    public void deleteAllOffersForClient(long bankID) {
        creditOfferRepository.deleteAllOffersForClient(bankID);
    }

    public void save(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

}
