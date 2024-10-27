package unify.basket.fixture;

import unify.basket.entities.Price;

import java.time.LocalDate;

public class PriceFixture {
    public static Price createPriceWithValue(double value) {
        Price price = new Price();
        price.setValue(value);
        price.setStartDate(LocalDate.now());
        return price;
    }
}
