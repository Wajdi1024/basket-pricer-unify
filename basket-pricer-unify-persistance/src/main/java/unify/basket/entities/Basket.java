package unify.basket.entities;

import unify.basket.utils.BasketUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {

    private Long id;

    private List<Product> productList = new ArrayList<>();
    private BasketSpecialOffer specialOffer;

    private Double subTotal = 0.0;

    private Double totalPrice = 0.0;

    private Integer specialOfferApplied = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        this.productList.add(product);
        this.subTotal += product.getLastPrice().getValue();
        this.totalPrice += product.getPriceWithDiscount();
    }

    public void addProducts(List<Product> products) {
        products.forEach(product -> {
            this.productList.add(product);
            this.subTotal += product.getLastPrice().getValue();
            this.totalPrice += product.getPriceWithDiscount();
        });
    }


    public boolean removeProduct(Product product) {
        return this.productList.remove(product);
    }

    public boolean removeListProduct(List<Product> product) {
        return this.productList.removeAll(product);
    }

    public void clearProducts() {
        subTotal = 0.0;
        totalPrice = 0.0;
        productList.clear();
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(this.productList);
    }

    public BasketSpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    public void applySpecialOffer(BasketSpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
        if (EnumDiscountType.PERCENT.equals(specialOffer.getDiscountType())) {
            int count = BasketUtils.countSublistOccurrences(productList, specialOffer.getPurchasedProducts());
            specialOfferApplied = count;
            specialOffer.getOfferedProducts().forEach(p -> {
                int productFrequency = Collections.frequency(productList, p);
                if (productFrequency >= count) {
                    totalPrice -= count * (p.getLastPrice().getValue() * specialOffer.getDiscountValue());
                } else {
                    totalPrice -= (p.getLastPrice().getValue() * specialOffer.getDiscountValue());
                }
            });
        }
    }

    public Double getSubTotal() {
        return subTotal;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

}
