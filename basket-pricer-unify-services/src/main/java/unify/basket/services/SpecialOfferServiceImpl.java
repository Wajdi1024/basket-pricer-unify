package unify.basket.services;

import unify.basket.entities.SpecialOffer;
import unify.basket.repositories.SpecialOfferRepository;

import java.util.List;

public class SpecialOfferServiceImpl implements SpecialOfferService{

    private SpecialOfferRepository specialOfferRepository = new SpecialOfferRepository();
    @Override
    public SpecialOffer add(SpecialOffer specialOffer) {
        return specialOfferRepository.insertOne(specialOffer);
    }

    @Override
    public List<SpecialOffer> listAvailableSpecialOffers() {
        return specialOfferRepository.listAllAvailableOffers();
    }
}
