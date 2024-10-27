package unify.basket.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import unify.basket.entities.Basket;
import unify.basket.exceptions.BasketCreationException;
import unify.basket.repositories.BasketRepository;
import unify.basket.repositories.ProductRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unify.basket.services.fixture.BasketPricerFixture.createProductWithNameAndPrice;
import static unify.basket.services.fixture.BasketPricerFixture.createProductWithSpecialOffer;

@ExtendWith(MockitoExtension.class)
class BasketPricerImplTest {

    @InjectMocks
    BasketPricerImpl basketPricer;

    @Mock
    ProductRepository productRepository;

    @Mock
    private BasketRepository basketRepository;


    @BeforeEach
    void setUp() {

    }

    @Test
    void createBasket_withoutDiscount() throws BasketCreationException {
        ArgumentCaptor<Basket> basketArgumentCaptor = ArgumentCaptor.forClass(Basket.class);
        Mockito.when(productRepository.findById("Soap"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Soap", 10L)));
        Mockito.when(productRepository.findById("Bread"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Bread", 10L)));
        Mockito.when(productRepository.findById("Milk"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Milk", 15L)));
        Mockito.when(productRepository.findById("Apples"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Apples", 10L)));
        basketPricer.createBasket(Arrays.asList("Soap", "Bread", "Milk", "Apples"));
        Mockito.verify(basketRepository).insertOne(basketArgumentCaptor.capture());
        assertEquals(45L, basketArgumentCaptor.getValue().getTotalPrice());
        assertEquals(45L, basketArgumentCaptor.getValue().getSubTotal());
    }


    @Test
    void createBasket_creationException() {
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(BasketCreationException.class, () -> basketPricer.createBasket(Arrays.asList("Unknown")));
    }


    @Test
    void createBasket_withDiscountOnProduct() throws BasketCreationException {
        ArgumentCaptor<Basket> basketArgumentCaptor = ArgumentCaptor.forClass(Basket.class);
        Mockito.when(productRepository.findById("Bread"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Bread", 0.80)));
        Mockito.when(productRepository.findById("Milk"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Milk", 1.30)));
        Mockito.when(productRepository.findById("Apples"))
                .thenReturn(Optional.of(createProductWithSpecialOffer("Apples", 1, 0.1)));
        basketPricer.createBasket(Arrays.asList("Bread", "Milk", "Apples"));
        Mockito.verify(basketRepository).insertOne(basketArgumentCaptor.capture());
        assertEquals(3.00, basketArgumentCaptor.getValue().getTotalPrice());
        assertEquals(3.10, basketArgumentCaptor.getValue().getSubTotal());
    }


    @Test
    void createBasket_withDiscountBasket() throws BasketCreationException {
        ArgumentCaptor<Basket> basketArgumentCaptor = ArgumentCaptor.forClass(Basket.class);
        Mockito.when(productRepository.findById("Bread"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Bread", 0.80)));
        Mockito.when(productRepository.findById("Milk"))
                .thenReturn(Optional.of(createProductWithNameAndPrice("Milk", 1.30)));
        Mockito.when(productRepository.findById("Apples"))
                .thenReturn(Optional.of(createProductWithSpecialOffer("Apples", 1, 0.1)));
        basketPricer.createBasket(Arrays.asList("Bread", "Milk", "Apples"));
        Mockito.verify(basketRepository).insertOne(basketArgumentCaptor.capture());
        assertEquals(3.00, basketArgumentCaptor.getValue().getTotalPrice());
        assertEquals(3.10, basketArgumentCaptor.getValue().getSubTotal());
    }



}