package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Frequency;

/**
 * A DTO for the DueDate entity.
 */
public class DueDateDTO implements Serializable {

    private Long id;

    @NotNull
    private String paymentMethod;

    @NotNull
    private Integer installments;

    private String dayDesc;

    private Integer paymentDay;

    private Frequency frequency;


    private Long collegeId;

    private Long branchId;

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

    public Integer getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DueDateDTO dueDateDTO = (DueDateDTO) o;
        if (dueDateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dueDateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DueDateDTO{" +
            "id=" + getId() +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", installments=" + getInstallments() +
            ", dayDesc='" + getDayDesc() + "'" +
            ", paymentDay=" + getPaymentDay() +
            ", frequency='" + getFrequency() + "'" +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            "}";
    }
}
