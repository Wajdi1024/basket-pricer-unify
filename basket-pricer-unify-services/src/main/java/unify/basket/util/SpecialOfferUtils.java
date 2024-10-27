package unify.basket.util;

import unify.basket.entities.Basket;
import unify.basket.entities.BasketSpecialOffer;
import unify.basket.entities.Product;
import unify.basket.entities.SpecialOffer;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SpecialOfferUtils {
    private SpecialOfferUtils() {
    }

    public static boolean isAvailable(SpecialOffer specialOffer) {
        return Objects.isNull(specialOffer.getEndDate()) || (LocalDate.now().isEqual(specialOffer.getStartDate()) ||
                (LocalDate.now().isAfter(specialOffer.getStartDate()) && LocalDate.now().isBefore(specialOffer.getEndDate())));
    }

    public static boolean isSpecialOfferValidOnBasket(BasketSpecialOffer basketSpecialOffer, Basket basket) {
        return basket.getProductList().containsAll(basketSpecialOffer.getPurchasedProducts())
                && basket.getProductList().containsAll(basketSpecialOffer.getOfferedProducts());

    }
}
