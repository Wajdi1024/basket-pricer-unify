package unify.basket.repositories;

import unify.basket.entities.BasketSpecialOffer;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;

import java.util.*;

public class SpecialOfferRepository implements AbstractRepository<SpecialOffer, Long> {

    private static final Set<SpecialOffer> specialOffers = new HashSet<>();

    private static Long nbSpecialOffre = 1l;
    @Override
    public Optional<SpecialOffer> findById(Long aLong) {
        return specialOffers.stream().filter(sp -> aLong.equals(sp.getSpecialOfferId())).findFirst();
    }

    @Override
    public List<SpecialOffer> findAll() {
        return new ArrayList<>(specialOffers);
    }

    @Override
    public SpecialOffer insertOne(SpecialOffer specialOffer) {
        specialOffer.setSpecialOfferId(nbSpecialOffre++);
         specialOffers.add(specialOffer);
        return specialOffer;
    }

    @Override
    public SpecialOffer updateOne(SpecialOffer specialOffer) {
        return null;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }

    @Override
    public boolean delete(SpecialOffer specialOffer) {
        return false;
    }

    public Optional<SpecialOffer> findBasketSpecialOfferByProucts(List<Product> products) {
        Optional<SpecialOffer> optionalSpecialOffer= specialOffers.stream().filter(specialOffer -> {
            if(specialOffer instanceof BasketSpecialOffer) {
                return products.containsAll(((BasketSpecialOffer) specialOffer).getPurchasedProducts()) &&
                        products.containsAll(((BasketSpecialOffer) specialOffer).getOfferedProducts());
            }
            return false;
        }).findFirst();
        return optionalSpecialOffer;
    }
}
