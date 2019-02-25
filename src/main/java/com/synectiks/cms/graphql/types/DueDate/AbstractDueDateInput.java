package com.synectiks.cms.graphql.types.DueDate;

import com.synectiks.cms.domain.enumeration.Frequency;

import java.util.Objects;

public class AbstractDueDateInput {
    private Long id;
    private String paymentMethod;
    private Integer installments;
    private String dayDesc;
    private Frequency frequency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public String getDayDesc() {
        return dayDesc;
    }

    public void setDayDesc(String dayDesc) {
        this.dayDesc = dayDesc;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDueDateInput that = (AbstractDueDateInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(paymentMethod, that.paymentMethod) &&
            Objects.equals(installments, that.installments) &&
            Objects.equals(dayDesc, that.dayDesc) &&
            frequency == that.frequency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, installments, dayDesc, frequency);
    }

    @Override
    public String toString() {
        return "AbstractDueDateInput{" +
            "id=" + id +
            ", paymentMethod='" + paymentMethod + '\'' +
            ", installments=" + installments +
            ", dayDesc='" + dayDesc + '\'' +
            ", frequency=" + frequency +
            '}';
    }
}
