package com.example.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Offer;
import com.entity.OfferApplied;
import com.example.dao.OfferAppliedDao;
import com.example.dao.OfferDao;

import java.util.List;
import java.util.Optional;

@Service
public class OfferAppliedService {

    @Autowired
    private OfferAppliedDao offerAppliedDAO;

    public List<OfferApplied> getAllOfferApplied() {
        return offerAppliedDAO.findAll();
    }

    public Optional<OfferApplied> getOfferAppliedById(Long id) {
        return offerAppliedDAO.findById(id);
    }

    public OfferApplied saveOfferApplied(Long OfferId, Long BookingId) {
        OfferApplied offerApplied=new OfferApplied();
        offerApplied.setOffer(new Offer());
        offerApplied.getOffer().setOfferId(OfferId);
        offerApplied.setBookingId(BookingId);
        return offerAppliedDAO.save(offerApplied);

    }
}
