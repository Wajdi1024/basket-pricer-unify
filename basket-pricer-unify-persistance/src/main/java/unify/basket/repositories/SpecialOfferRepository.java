package unify.basket.repositories;

import unify.basket.entities.BasketSpecialOffer;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;
import unify.basket.exceptions.EntityNotFound;
import unify.basket.utils.SpecialOfferUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SpecialOfferRepository implements BasketSpecialOfferRepository {

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
        Optional<SpecialOffer> optionalSpecialOffer = findById(specialOffer.getSpecialOfferId());
        if (optionalSpecialOffer.isEmpty()) {
            throw new EntityNotFound("Special Offer not found");
        }
        SpecialOffer newSpecialOffer = optionalSpecialOffer.get();
        newSpecialOffer.setDiscountValue(specialOffer.getDiscountValue());
        newSpecialOffer.setStartDate(specialOffer.getStartDate());
        newSpecialOffer.setEndDate(specialOffer.getEndDate());
        newSpecialOffer.setDiscountType(specialOffer.getDiscountType());
        if (specialOffer instanceof BasketSpecialOffer) {
            BasketSpecialOffer basketSpecialOffer = (BasketSpecialOffer) specialOffer;
            BasketSpecialOffer newBasketSpecialOffer = (BasketSpecialOffer) newSpecialOffer;
            newBasketSpecialOffer.setOfferedProducts(basketSpecialOffer.getOfferedProducts());
            newBasketSpecialOffer.setPurchasedProducts(basketSpecialOffer.getPurchasedProducts());
        }
        return newSpecialOffer;
    }

    @Override
    public boolean deleteById(Long aLong) {
        Optional<SpecialOffer> specialOffer = specialOffers.stream().filter(p -> p.getSpecialOfferId().equals(aLong)).findFirst();
        return specialOffer.filter(this::delete).isPresent();
    }

    @Override
    public boolean delete(SpecialOffer specialOffer) {
        return specialOffers.remove(specialOffer);
    }

    @Override
    public Optional<SpecialOffer> findByProducts(List<Product> products) {
        Optional<SpecialOffer> optionalSpecialOffer = specialOffers.stream().filter(specialOffer -> {
            if (specialOffer instanceof BasketSpecialOffer) {
                return products.containsAll(((BasketSpecialOffer) specialOffer).getPurchasedProducts()) && products.containsAll(((BasketSpecialOffer) specialOffer).getOfferedProducts());
            }
            return false;
        }).findFirst();
        return optionalSpecialOffer;
    }

    @Override
    public List<SpecialOffer> listAllAvailableOffers() {
        return specialOffers.stream().filter(SpecialOfferUtils::isAvailable).collect(Collectors.toList());
    }

}
