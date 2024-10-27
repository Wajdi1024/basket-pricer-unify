package unify.basket.services;

import unify.basket.entities.SpecialOffer;
import unify.basket.repositories.SpecialOfferRepository;

public class SpecialOfferServiceImpl implements SpecialOfferService{

    private SpecialOfferRepository specialOfferRepository = new SpecialOfferRepository();
    @Override
    public SpecialOffer add(SpecialOffer specialOffer) {
        return specialOfferRepository.insertOne(specialOffer);
    }
}
