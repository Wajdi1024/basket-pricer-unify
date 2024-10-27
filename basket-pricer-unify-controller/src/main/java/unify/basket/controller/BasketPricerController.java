package unify.basket.controller;

import unify.basket.entities.*;
import unify.basket.exceptions.BasketCreationException;
import unify.basket.services.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class BasketPricerController {

    private final BasketPricer basketPricer = new BasketPricerImpl();

    private final ProductService productService = new ProductServiceImpl();

    private final SpecialOfferService specialOfferService = new SpecialOfferServiceImpl();

    public Basket buy(String... productsNames) throws BasketCreationException {
        return basketPricer.createBasket(Arrays.asList(productsNames));
    }

    public List<Product> getAllProdcuts() {
        return productService.findAll();
    }

    public List<SpecialOffer> listAvailablesSpecialOffers() {
        return specialOfferService.listAvailableSpecialOffers();
    }

    public void initFakeData() {
        Product souap = new Product();
        souap.setName("Soup");
        Price price = new Price();
        price.setValue(0.60);
        souap.addPrice(price);
        souap.setEnumProductUnit(EnumProductUnit.TIN);
        productService.add(souap);
        Product bread = new Product();
        bread.setName("Bread");
        Price breadPrice = new Price();
        breadPrice.setValue(0.80);
        bread.addPrice(breadPrice);
        bread.setEnumProductUnit(EnumProductUnit.LOAF);
        productService.add(bread);
        Product milk = new Product();
        milk.setName("Milk");
        Price milkPrice = new Price();
        milkPrice.setValue(1.30);
        milk.addPrice(milkPrice);
        milk.setEnumProductUnit(EnumProductUnit.BOTTLE);
        productService.add(milk);
        BasketSpecialOffer basketSpecialOffer = new BasketSpecialOffer();
        basketSpecialOffer.getPurchasedProducts().add(souap);
        basketSpecialOffer.getPurchasedProducts().add(souap);
        basketSpecialOffer.getOfferedProducts().add(bread);
        basketSpecialOffer.setDiscountType(EnumDiscountType.PERCENT);
        basketSpecialOffer.setDiscountValue(0.5);
        basketSpecialOffer.setLabel("Buy 2 tins of soup and get a loaf of bread for half price");
        specialOfferService.add(basketSpecialOffer);
        Product apples = new Product();
        apples.setName("Apples");
        Price applesPrice = new Price();
        applesPrice.setValue(1.30);
        apples.addPrice(applesPrice);
        apples.setEnumProductUnit(EnumProductUnit.BAG);
        ProductSpecialOffer applesSpecialOffer = new ProductSpecialOffer();
        applesSpecialOffer.setDiscountType(EnumDiscountType.PERCENT);
        applesSpecialOffer.setDiscountValue(0.10);
        applesSpecialOffer.setStartDate(LocalDate.now());
        applesSpecialOffer.setEndDate(LocalDate.now().plusDays(30));
        applesSpecialOffer.setLabel("Apples have a 10% discount off their normal price this week");
        specialOfferService.add(applesSpecialOffer);
        apples.addSpecialOffer(applesSpecialOffer);
        productService.add(apples);
    }

}
