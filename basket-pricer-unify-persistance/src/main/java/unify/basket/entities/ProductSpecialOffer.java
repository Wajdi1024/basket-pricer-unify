package unify.basket.entities;

import unify.basket.utils.CurrencyUtils;

public class ProductSpecialOffer extends SpecialOffer {

    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        if (discountType == EnumDiscountType.PERCENT) {
            return product.getName() + " " + (int) (discountValue * 100) + " off: -" + CurrencyUtils.format(product.getLastPrice().getValue() * discountValue);
        }
        if (discountType == EnumDiscountType.GET_FREE) {
            return product.getName() + "off: -" + CurrencyUtils.format(product.getLastPrice().getValue());
        }
        return product.getName();
    }
}
