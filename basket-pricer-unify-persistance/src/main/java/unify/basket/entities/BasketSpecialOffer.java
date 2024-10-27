package unify.basket.entities;

import unify.basket.utils.CurrencyUtils;

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

    @Override
    public String toString() {
        if (discountType == EnumDiscountType.PERCENT) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Product offered : offeredProducts) {
                stringBuilder.append(offered.getName() + " " + (int) (discountValue * 100) + " off: -"
                        + CurrencyUtils.format(offered.getLastPrice().getValue() * discountValue));
            }
            return stringBuilder.toString();
        }
        return label;
    }

}
