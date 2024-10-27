package unify.basket.services.fixture;

import unify.basket.entities.Price;
import unify.basket.entities.Product;
import unify.basket.entities.ProductSpecialOffer;

public class BasketPricerFixture {

    public static Product createProductWithNameAndPrice(String name, double value) {
        Product p = new Product();
        p.setName(name);
        p.addPrice(createPriceWithName(value));
        return p;
    }

    public static Price createPriceWithName(double value) {
        Price price = new Price();
        price.setValue(value);
        return price;
    }

    public static Product createProductWithSpecialOffer(String name, double value, double discountValue) {
        Product p = createProductWithNameAndPrice(name,value);
        ProductSpecialOffer productSpecialOffer = new ProductSpecialOffer();
        productSpecialOffer.setDiscountValue(discountValue);
        p.addSpecialOffer(productSpecialOffer);
        return p;
    }

}