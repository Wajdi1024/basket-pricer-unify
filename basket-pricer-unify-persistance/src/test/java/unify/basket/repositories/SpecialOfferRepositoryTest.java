package unify.basket.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unify.basket.entities.BasketSpecialOffer;
import unify.basket.entities.SpecialOffer;
import unify.basket.exceptions.EntityNotFound;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SpecialOfferRepositoryTest {

    BasketSpecialOfferRepository specialOfferRepository = new SpecialOfferRepository();

    @BeforeEach
    void setUp() {
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.setStartDate(LocalDate.now());
        basketSpecialOffer.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer.setDiscountValue(0.25);
        specialOfferRepository.insertOne(basketSpecialOffer);
        BasketSpecialOffer basketSpecialOffer1 = new BasketSpecialOffer();
        basketSpecialOffer1.setStartDate(LocalDate.now());
        basketSpecialOffer1.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer1.setDiscountValue(0.25);
        specialOfferRepository.insertOne(basketSpecialOffer1);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById_specialOfferFound() {
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.setStartDate(LocalDate.now());
        basketSpecialOffer.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer.setDiscountValue(0.25);
        specialOfferRepository.insertOne(basketSpecialOffer);
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(basketSpecialOffer.getSpecialOfferId());
        assertTrue(specialOffer.isPresent());
    }


    @Test
    void findById_specialOfferNotFound() {
        Optional<SpecialOffer> specialOffer = specialOfferRepository.findById(500L);
        assertTrue(specialOffer.isEmpty());
    }


    @Test
    void findAll_success() {
        assertFalse(specialOfferRepository.findAll().isEmpty());
    }

    @Test
    void insertOne_success() {
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.setStartDate(LocalDate.now());
        basketSpecialOffer.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer.setDiscountValue(0.25);
        SpecialOffer specialOffer = specialOfferRepository.insertOne(basketSpecialOffer);
        assertNotNull(specialOffer.getSpecialOfferId());
    }

    @Test
    void updateOne_success() {
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.setStartDate(LocalDate.now());
        basketSpecialOffer.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer.setDiscountValue(0.25);
        SpecialOffer specialOffer = specialOfferRepository.insertOne(basketSpecialOffer);
        BasketSpecialOffer newBasketSpecialOffer = new BasketSpecialOffer();
        newBasketSpecialOffer.setStartDate(LocalDate.now());
        newBasketSpecialOffer.setEndDate(null);
        newBasketSpecialOffer.setDiscountValue(0.25);
        newBasketSpecialOffer.setSpecialOfferId(specialOffer.getSpecialOfferId());
        assertEquals(specialOfferRepository.updateOne(newBasketSpecialOffer).getEndDate(), null);
    }

    @Test
    void updateOne_notFound() {
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.setStartDate(LocalDate.now());
        basketSpecialOffer.setEndDate(LocalDate.now().plusDays(5));
        basketSpecialOffer.setDiscountValue(0.25);
        basketSpecialOffer.setSpecialOfferId(5000L);
        assertThrows(EntityNotFound.class, () -> specialOfferRepository.updateOne(basketSpecialOffer));
    }


    @Test
    void deleteById_success() {
        assertTrue(specialOfferRepository.deleteById(1L));
        assertTrue(specialOfferRepository.findById(1L).isEmpty());
    }

    @Test
    void deleteById_specialOfferNotFound() {
        assertFalse(specialOfferRepository.deleteById(100000L));
    }


    @Test
    void delete_succes() {
        SpecialOffer specialOffer = new BasketSpecialOffer();
        specialOffer.setSpecialOfferId(2L);
        assertTrue(specialOfferRepository.delete(specialOffer));
    }

    @Test
    void delete_specialOfferNotFound() {
        SpecialOffer specialOffer = new BasketSpecialOffer();
        specialOffer.setSpecialOfferId(50000L);
        assertFalse(specialOfferRepository.delete(specialOffer));
    }


    @Test
    void findByProducts() {
    }
}