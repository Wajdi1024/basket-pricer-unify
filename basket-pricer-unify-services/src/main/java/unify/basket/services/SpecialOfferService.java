package unify.basket.services;

import unify.basket.entities.SpecialOffer;

import java.util.List;

public interface SpecialOfferService {

    SpecialOffer add(SpecialOffer specialOffer);

    List<SpecialOffer> listAvailableSpecialOffers();
}
