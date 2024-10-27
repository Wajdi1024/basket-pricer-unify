package unify.basket.entities;

import java.time.LocalDate;

public abstract class SpecialOffer {

    private Long specialOfferId;

    private LocalDate startDate;
    private LocalDate endDate;
    private EnumDiscountType discountType;
    private Double discountValue;

    public Long getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(Long specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EnumDiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(EnumDiscountType discountType) {
        this.discountType = discountType;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }
}
