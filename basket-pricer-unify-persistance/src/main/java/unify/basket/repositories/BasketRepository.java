package unify.basket.repositories;

import unify.basket.entities.Basket;
import unify.basket.exceptions.EntityNotFound;

import java.util.*;

public class BasketRepository implements AbstractRepository<Basket, Long>, GeneratorId {

    private static final Set<Basket> baskets = new HashSet<>();

    private static Long nbBaskets = 1L;

    @Override
    public Optional<Basket> findById(Long aLong) {
        return baskets.stream().filter(b -> aLong.equals(b.getId())).findFirst();
    }

    @Override
    public List<Basket> findAll() {
        return new ArrayList<>(baskets);
    }

    @Override
    public Basket insertOne(Basket basket) {
        basket.setId(nbBaskets++);
        baskets.add(basket);
        return basket;
    }

    @Override
    public Basket updateOne(Basket basket) {
        Optional<Basket> optBasket = findById(basket.getId());
        if (optBasket.isEmpty()) {
            throw new EntityNotFound("Basket not found");
        }
        Basket newBasket = optBasket.get();
        newBasket.applySpecialOffer(basket.getSpecialOffer());
        newBasket.addProducts(basket.getProductList());
        return newBasket;
    }

    @Override
    public boolean deleteById(Long aLong) {
        Optional<Basket> optionalBasket = baskets.stream().filter(p -> p.getId().equals(aLong)).findFirst();
        return optionalBasket.filter(this::delete).isPresent();
    }

    @Override
    public boolean delete(Basket basket) {
        return baskets.remove(basket);
    }

    @Override
    public Long getLastId() {
        return Long.valueOf(nbBaskets);
    }
}
