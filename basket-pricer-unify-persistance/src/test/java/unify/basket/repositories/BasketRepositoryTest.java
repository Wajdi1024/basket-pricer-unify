package unify.basket.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unify.basket.entities.Basket;
import unify.basket.entities.BasketSpecialOffer;
import unify.basket.fixture.BasketFixture;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BasketRepositoryTest {

    private BasketRepository basketRepository;

    @BeforeEach
    void setUp() {
        basketRepository = new BasketRepository();
        basketRepository.insertOne(BasketFixture.createBasket());
    }

    @Test
    void insertOne_success() {
        Basket basket = new Basket();
        basket.setSubTotal(100.0);
        basket.setTotalPrice(120.0);
        basket.applySpecialOffer(new BasketSpecialOffer());
        Basket insertedBasket = basketRepository.insertOne(basket);
        assertNotNull(insertedBasket.getId());
    }

    @Test
    void findById_succes() {
        Optional<Basket> foundBasket = basketRepository.findById(1L);
        assertTrue(foundBasket.isPresent());
    }

    @Test
    void testFindAll() {
    }

    @Test
    void testUpdateOne() {
    }

    @Test
    void testUpdateOne_EntityNotFound() {
    }

    @Test
    void testDeleteById() {
    }

    @Test
    void testDeleteById_NotFound() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testGetLastId() {
    }
}