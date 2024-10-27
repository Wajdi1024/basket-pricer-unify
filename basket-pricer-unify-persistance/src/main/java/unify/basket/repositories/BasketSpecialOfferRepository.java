package unify.basket.repositories;

import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;

import java.util.List;
import java.util.Optional;

public interface BasketSpecialOfferRepository extends AbstractRepository<SpecialOffer, Long> {
    Optional<SpecialOffer> findByProducts(List<Product> products);

    List<SpecialOffer> listAllAvailableOffers();
}
