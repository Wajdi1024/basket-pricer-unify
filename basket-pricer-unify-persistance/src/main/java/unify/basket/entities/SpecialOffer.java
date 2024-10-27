package unify.basket.entities;

import java.time.LocalDate;
import java.util.Objects;

public abstract class SpecialOffer {

    protected Long specialOfferId;

    protected String label;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected EnumDiscountType discountType;
    protected Double discountValue;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialOffer that = (SpecialOffer) o;
        return Objects.equals(specialOfferId, that.specialOfferId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialOfferId);
    }
}
