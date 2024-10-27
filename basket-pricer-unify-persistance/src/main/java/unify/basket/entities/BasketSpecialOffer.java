package unify.basket.entities;

import java.util.ArrayList;
import java.util.List;

public class BasketSpecialOffer extends SpecialOffer {

    private List<Product> purchasedProducts = new ArrayList<>();

    private List<Product> offeredProducts = new ArrayList<>();


    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public List<Product> getOfferedProducts() {
        return offeredProducts;
    }

    public void setOfferedProducts(List<Product> offeredProducts) {
        this.offeredProducts = offeredProducts;
    }
}
