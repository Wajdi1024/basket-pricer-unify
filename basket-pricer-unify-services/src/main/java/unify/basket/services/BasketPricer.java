package unify.basket.services;

import unify.basket.entities.Basket;
import unify.basket.exceptions.BasketCreationException;

import java.util.List;

public interface BasketPricer {

    Basket createBasket(List<String> products) throws BasketCreationException;
}
