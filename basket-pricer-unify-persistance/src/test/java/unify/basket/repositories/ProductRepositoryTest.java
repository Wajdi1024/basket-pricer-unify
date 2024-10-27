package unify.basket.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unify.basket.entities.Product;
import unify.basket.exceptions.DuplicateKeyException;
import unify.basket.exceptions.EntityNotFound;
import unify.basket.exceptions.InvalidKeyException;
import unify.basket.fixture.ProductFixture;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {


    private ProductRepository productRepository = new ProductRepository();

    @BeforeEach
    void setUp() {
        productRepository.insertOne(ProductFixture.createSoup());
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteById("soup");
        productRepository.deleteById("bread");
    }

    @Test
    void findById_oneProductFound() {
        Optional<Product> soup = productRepository.findById("soup");
        assertTrue(soup.isPresent());
    }

    @Test
    void findById_noProductFound() {
        Optional<Product> soup = productRepository.findById("soup2");
        assertTrue(soup.isEmpty());
    }


    @Test
    void findAll() {
        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
    }

    @Test
    void insertOne_productAdded() {
        Product p = productRepository.insertOne(ProductFixture.createWithName("bread"));
        assertEquals("bread", p.getName());
    }

    @Test
    void insertOne_throwDuplicateKeyException() {
        assertThrows(DuplicateKeyException.class,
                () -> productRepository.insertOne(ProductFixture.createSoup()));
    }

    @Test
    void insertOne_throwInvalidKeyException() {
        assertThrows(InvalidKeyException.class,
                () -> productRepository.insertOne(ProductFixture.createWithName("")));
    }

    @Test
    void updateOne_ok() {
        Product p = productRepository.updateOne(ProductFixture.createWithName("soup"));
        assertNotNull(p);
    }


    @Test
    void updateOne_productNotFound() {
        assertThrows(EntityNotFound.class,
                () -> productRepository.updateOne(ProductFixture.createWithName("Unknown")));
    }


    @Test
    void deleteById_productDoesNotExist() {
        boolean result = productRepository.delete(ProductFixture.createWithName("PC"));
        assertFalse(result);
    }

    @Test
    void deleteById_productExist() {
        boolean result = productRepository.delete(ProductFixture.createSoup());
        assertTrue(result);
    }


    @Test
    void deleteById_productExist_checkListSize() {
        productRepository.insertOne(ProductFixture.createWithName("bread"));
        productRepository.deleteById("soup");
        productRepository.deleteById("bread");
        assertEquals(0, productRepository.findAll().size());
    }


    @Test
    void delete_productExist() {
        boolean result = productRepository.delete(ProductFixture.createSoup());
        assertTrue(result);
    }
}