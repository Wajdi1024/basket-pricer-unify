package unify.basket.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unify.basket.entities.Price;
import unify.basket.exceptions.EntityNotFound;
import unify.basket.fixture.PriceFixture;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PriceRepositoryTest {

    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository = new PriceRepository();
        priceRepository.insertOne(PriceFixture.createPriceWithValue(12.5));
        priceRepository.insertOne(PriceFixture.createPriceWithValue(20));
    }

    @AfterEach
    void tearDown() {
        priceRepository.findAll().forEach(price -> priceRepository.delete(price));
        priceRepository = null;
    }

    @Test
    void findById_anExistingId() {
        assertNotNull(priceRepository.findById(1L));
    }

    @Test
    void findById_idDoesNotExist() {
        assertNotNull(priceRepository.findById(999L));
    }


    @Test
    void findAll_checkSize() {
        assertEquals(2, priceRepository.findAll().size());
    }

    @Test
    void insertOne_success() {
        Price price = priceRepository.insertOne(PriceFixture.createPriceWithValue(30));
        assertNotNull(price.getId());
    }

    @Test
    void updateOne_anExistingProduct() {
        Price price = PriceFixture.createPriceWithValue(10);
        price.setId(1L);
        LocalDate localDate = LocalDate.now();
        price.setEndDate(localDate);
        Price updateProduct = priceRepository.updateOne(price);
        assertEquals(localDate, updateProduct.getEndDate());
    }

    @Test
    void updateOne_priceNotFound() {
        Price price = PriceFixture.createPriceWithValue(10);
        price.setId(999L);
        LocalDate localDate = LocalDate.now();
        price.setEndDate(localDate);
        assertThrows(EntityNotFound.class, () -> priceRepository.updateOne(price));
    }

    @Test
    void deleteById_priceExist() {
        assertTrue(priceRepository.deleteById(priceRepository.getLastId() - 1));
    }

    @Test
    void deleteById_priceDoesNotExist() {
        assertFalse(priceRepository.deleteById(999L));
    }

    @Test
    void delete() {
        Price price = PriceFixture.createPriceWithValue(2L);
        price.setId(priceRepository.getLastId() - 1);
        assertTrue(priceRepository.delete(price));
    }
}