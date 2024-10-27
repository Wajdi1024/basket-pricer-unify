package unify.basket.controller;

import unify.basket.entities.*;
import unify.basket.exceptions.BasketCreationException;
import unify.basket.services.*;

import java.util.Arrays;
import java.util.List;

public class BasketPricerController {

    private BasketPricer basketPricer = new BasketPricerImpl();

    private ProductService productService = new ProductServiceImpl();

    private SpecialOfferService specialOfferService = new SpecialOfferServiceImpl();

    public Basket buy(String... productsNames) throws BasketCreationException {
        return basketPricer.createBasket(Arrays.asList(productsNames));
    }

    public List<Product> getAllProdcuts() {
        return productService.findAll();
    }

    public void initFakeData() {
        Product souap = new Product();
        souap.setName("Soup");
        Price price = new Price();
        price.setValue(0.60);
        souap.addPrice(price);
        productService.add(souap);
        Product bread = new Product();
        bread.setName("Bread");
        Price breadPrice = new Price();
        breadPrice.setValue(0.80);
        bread.addPrice(breadPrice);
        productService.add(bread);
        Product milk = new Product();
        milk.setName("Milk");
        Price milkPrice = new Price();
        milkPrice.setValue(1.30);
        milk.addPrice(milkPrice);
        productService.add(milk);
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.getPurchasedProducts().add(bread);
        basketSpecialOffer.getPurchasedProducts().add(souap);
        basketSpecialOffer.getOfferedProducts().add(milk);
        basketSpecialOffer.setDiscountType(EnumDiscountType.PERCENT);
        basketSpecialOffer.setDiscountValue(0.5);
        specialOfferService.add(basketSpecialOffer);
    }

}
