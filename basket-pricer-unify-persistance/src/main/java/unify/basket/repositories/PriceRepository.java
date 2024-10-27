package unify.basket.repositories;

import unify.basket.entities.Price;
import unify.basket.entities.Product;
import unify.basket.exceptions.EntityNotFound;

import java.util.*;

public class PriceRepository implements AbstractRepository<Price, Long>, GeneratorId {

    private static final Set<Price> prices = new HashSet<>();

    private  static Long nbPrice = 1L;
    @Override
    public Optional<Price> findById(Long aLong) {
        return prices.stream().filter(p -> aLong.equals(p.getId())).findFirst();
    }

    @Override
    public List<Price> findAll() {
        return new ArrayList<>(prices);
    }

    @Override
    public Price insertOne(Price price) {
        price.setId(nbPrice++);
        prices.add(price);
        return price;
    }

    @Override
    public Price updateOne(Price price) {
        Optional<Price> optPrice = findById(price.getId());
        if(optPrice.isEmpty()) {
            throw new EntityNotFound("Price not found");
        }
        Price newPrice = optPrice.get();
        newPrice.setValue(price.getValue());
        newPrice.setStartDate(price.getStartDate());
        newPrice.setEndDate(price.getEndDate());
        return newPrice;

    }

    @Override
    public boolean deleteById(Long aLong) {
        Optional<Price> optionalPrice = prices.stream().filter(p -> p.getId().equals(aLong)).findFirst();
        return optionalPrice.filter(this::delete).isPresent();
    }

    @Override
    public boolean delete(Price price) {
        return prices.remove(price);
    }

    @Override
    public Long getLastId() {
        return Long.valueOf(nbPrice);
    }
}
