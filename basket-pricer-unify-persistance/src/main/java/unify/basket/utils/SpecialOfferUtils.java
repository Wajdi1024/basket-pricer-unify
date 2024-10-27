package unify.basket.utils;

import unify.basket.entities.SpecialOffer;

import java.time.LocalDate;
import java.util.Objects;

public class SpecialOfferUtils {
    private SpecialOfferUtils() {
    }

    public static boolean isAvailable(SpecialOffer specialOffer) {
        return Objects.isNull(specialOffer.getEndDate()) || (LocalDate.now().isEqual(specialOffer.getStartDate()) ||
                (LocalDate.now().isAfter(specialOffer.getStartDate()) && LocalDate.now().isBefore(specialOffer.getEndDate())));
    }

}
