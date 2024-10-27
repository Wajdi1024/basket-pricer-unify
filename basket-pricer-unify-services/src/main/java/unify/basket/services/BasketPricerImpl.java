package unify.basket.services;

import unify.basket.entities.Basket;
import unify.basket.entities.BasketSpecialOffer;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;
import unify.basket.exceptions.BasketCreationException;
import unify.basket.repositories.BasketRepository;
import unify.basket.repositories.ProductRepository;
import unify.basket.repositories.SpecialOfferRepository;
import unify.basket.utils.SpecialOfferUtils;

import java.util.List;
import java.util.Optional;

public class BasketPricerImpl implements BasketPricer {

    private ProductRepository productRepository = new ProductRepository();

    private BasketRepository basketRepository = new BasketRepository();

    private SpecialOfferRepository specialOfferRepository = new SpecialOfferRepository();

    @Override
    public Basket createBasket(List<String> products) throws BasketCreationException {
        Basket basket = new Basket();
        for (String currentProduct : products) {
            Optional<Product> optionalProduct = productRepository.findById(currentProduct);
            if (optionalProduct.isEmpty()) {
                throw new BasketCreationException(String.format("Basket can not be created, product[name=%s] does not exist", currentProduct));
            }
            basket.addProduct(optionalProduct.get());
        }
        Optional<SpecialOffer> basketSpecialOffer = specialOfferRepository.findByProducts(basket.getProductList());
        if(basketSpecialOffer.isPresent() && SpecialOfferUtils.isAvailable(basketSpecialOffer.get())) {
            basket.applySpecialOffer((BasketSpecialOffer) basketSpecialOffer.get());
        }
        return basketRepository.insertOne(basket);
    }
}
