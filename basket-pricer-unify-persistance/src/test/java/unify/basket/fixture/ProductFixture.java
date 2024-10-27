package unify.basket.fixture;

import unify.basket.entities.Product;

public class ProductFixture {

    public static Product createSoup() {
        return createWithName("soup");
    }

    public static Product createWithName(String name) {
        Product p = new Product();
        p.setName(name);
        return p;
    }

}
