package unify.basket.entities;

import org.apache.commons.lang3.StringUtils;
import unify.basket.utils.CurrencyUtils;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Product {

    private String name;

    EnumProductUnit enumProductUnit;
    private Deque<Price> pricesHistory = new ArrayDeque<>();
    private Deque<SpecialOffer> specialOffersHistory = new ArrayDeque<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getLastPrice() {
        return pricesHistory.peek();
    }

    public SpecialOffer getLastSpecialOffer() {
        return specialOffersHistory.peek();
    }

    public Deque<Price> getPricesHistory() {
        return pricesHistory;
    }

    public void setPricesHistory(Deque<Price> pricesHistory) {
        this.pricesHistory = pricesHistory;
    }

    public Deque<SpecialOffer> getSpecialOffersHistory() {
        return specialOffersHistory;
    }

    public void setSpecialOffersHistory(Deque<SpecialOffer> specialOffersHistory) {
        this.specialOffersHistory = specialOffersHistory;
    }

    public void addPrice(Price price) {
        if (this.pricesHistory.isEmpty()) {
            this.pricesHistory.addFirst(price);
            return;
        }
        Price oldPrice = this.pricesHistory.peek();
        LocalDate endDate = oldPrice.getEndDate();
        this.pricesHistory.addFirst(price);
        if (endDate == null) {
            oldPrice.setEndDate(LocalDate.now());
            return;
        }
        if (endDate.isAfter(LocalDate.now())) {
            oldPrice.setEndDate(LocalDate.now());
        }
    }

    public void addSpecialOffer(SpecialOffer specialOffer) {
        if (this.specialOffersHistory.isEmpty()) {
            this.specialOffersHistory.addFirst(specialOffer);
            return;
        }
        SpecialOffer oldOffer = this.specialOffersHistory.peek();
        LocalDate endDate = oldOffer.getEndDate();
        this.specialOffersHistory.addFirst(specialOffer);
        if (endDate == null) {
            oldOffer.setEndDate(LocalDate.now());
            return;
        }
        if (endDate.isAfter(LocalDate.now())) {
            oldOffer.setEndDate(LocalDate.now());
        }
    }

    public EnumProductUnit getEnumProductUnit() {
        return enumProductUnit;
    }

    public void setEnumProductUnit(EnumProductUnit enumProductUnit) {
        this.enumProductUnit = enumProductUnit;
    }

    public Double getPriceWithDiscount() {
        if (Objects.isNull(specialOffersHistory.peek())) {
            return this.getLastPrice().getValue();
        }
        return this.getLastPrice().getValue() - this.getLastPrice().getValue() * specialOffersHistory.peek().getDiscountValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public String toString() {
        return name + " - " + CurrencyUtils.format(pricesHistory.peek().getValue()) + " per " + StringUtils.lowerCase(String.valueOf(enumProductUnit));
    }
}
