package unify.basket.entities;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

public class Product {

    private String name;

    private Deque<Price> prices = new ArrayDeque<>();

    private Deque<SpecialOffer> specialOffers = new ArrayDeque<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getLastPrice() {
        return prices.peek();
    }

    public SpecialOffer getLastSpecialOffer() {
        return specialOffers.peek();
    }

    public Deque<Price> getPrices() {
        return prices;
    }

    public void setPrices(Deque<Price> prices) {
        this.prices = prices;
    }

    public Deque<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(Deque<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public void addPrice(Price price) {
        if (this.prices.isEmpty()) {
            this.prices.addFirst(price);
            return;
        }
        Price oldPrice = this.prices.peek();
        LocalDate endDate = oldPrice.getEndDate();
        this.prices.addFirst(price);
        if (endDate == null) {
            oldPrice.setEndDate(LocalDate.now());
            return;
        }
        if (endDate.isAfter(LocalDate.now())) {
            oldPrice.setEndDate(LocalDate.now());
        }
    }

    public void addSpecialOffer(SpecialOffer specialOffer) {
        if (this.specialOffers.isEmpty()) {
            this.specialOffers.addFirst(specialOffer);
            return;
        }
        SpecialOffer oldOffer = this.specialOffers.peek();
        LocalDate endDate = oldOffer.getEndDate();
        this.specialOffers.addFirst(specialOffer);
        if (endDate == null) {
            oldOffer.setEndDate(LocalDate.now());
            return;
        }
        if (endDate.isAfter(LocalDate.now())) {
            oldOffer.setEndDate(LocalDate.now());
        }
    }


    public Double getPriceWithDiscount() {
        if (Objects.isNull(specialOffers.peek())) {
            return this.getLastPrice().getValue();
        }
        return this.getLastPrice().getValue() - this.getLastPrice().getValue() * specialOffers.peek().getDiscountValue();
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
}
