package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PaymentRemainder entity.
 */
public class PaymentRemainderDTO implements Serializable {

    private Long id;

    @NotNull
    private String isAutoRemainder;

    private String isFirstPaymentRemainder;

    private Integer firstPaymentRemainderDays;

    private String isSecondPaymentRemainder;

    private Integer secondPaymentRemainderDays;

    private String isOverDuePaymentRemainder;

    private String overDuePaymentRemainderAfterDueDateOrUntilPaid;

    private Integer overDuePaymentRemainderDays;

    private String isRemainderRecipients;

    private String remainderRecipients;

    private Long dueDateId;

    private Long collegeId;

    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsAutoRemainder() {
        return isAutoRemainder;
    }

    public void setIsAutoRemainder(String isAutoRemainder) {
        this.isAutoRemainder = isAutoRemainder;
    }

    public String getIsFirstPaymentRemainder() {
        return isFirstPaymentRemainder;
    }

    public void setIsFirstPaymentRemainder(String isFirstPaymentRemainder) {
        this.isFirstPaymentRemainder = isFirstPaymentRemainder;
    }

    public Integer getFirstPaymentRemainderDays() {
        return firstPaymentRemainderDays;
    }

    public void setFirstPaymentRemainderDays(Integer firstPaymentRemainderDays) {
        this.firstPaymentRemainderDays = firstPaymentRemainderDays;
    }

    public String getIsSecondPaymentRemainder() {
        return isSecondPaymentRemainder;
    }

    public void setIsSecondPaymentRemainder(String isSecondPaymentRemainder) {
        this.isSecondPaymentRemainder = isSecondPaymentRemainder;
    }

    public Integer getSecondPaymentRemainderDays() {
        return secondPaymentRemainderDays;
    }

    public void setSecondPaymentRemainderDays(Integer secondPaymentRemainderDays) {
        this.secondPaymentRemainderDays = secondPaymentRemainderDays;
    }

    public String getIsOverDuePaymentRemainder() {
        return isOverDuePaymentRemainder;
    }

    public void setIsOverDuePaymentRemainder(String isOverDuePaymentRemainder) {
        this.isOverDuePaymentRemainder = isOverDuePaymentRemainder;
    }

    public String getOverDuePaymentRemainderAfterDueDateOrUntilPaid() {
        return overDuePaymentRemainderAfterDueDateOrUntilPaid;
    }

    public void setOverDuePaymentRemainderAfterDueDateOrUntilPaid(String overDuePaymentRemainderAfterDueDateOrUntilPaid) {
        this.overDuePaymentRemainderAfterDueDateOrUntilPaid = overDuePaymentRemainderAfterDueDateOrUntilPaid;
    }

    public Integer getOverDuePaymentRemainderDays() {
        return overDuePaymentRemainderDays;
    }

    public void setOverDuePaymentRemainderDays(Integer overDuePaymentRemainderDays) {
        this.overDuePaymentRemainderDays = overDuePaymentRemainderDays;
    }

    public String getIsRemainderRecipients() {
        return isRemainderRecipients;
    }

    public void setIsRemainderRecipients(String isRemainderRecipients) {
        this.isRemainderRecipients = isRemainderRecipients;
    }

    public String getRemainderRecipients() {
        return remainderRecipients;
    }

    public void setRemainderRecipients(String remainderRecipients) {
        this.remainderRecipients = remainderRecipients;
    }

    public Long getDueDateId() {
        return dueDateId;
    }

    public void setDueDateId(Long dueDateId) {
        this.dueDateId = dueDateId;
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

        PaymentRemainderDTO paymentRemainderDTO = (PaymentRemainderDTO) o;
        if (paymentRemainderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentRemainderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentRemainderDTO{" +
            "id=" + getId() +
            ", isAutoRemainder='" + getIsAutoRemainder() + "'" +
            ", isFirstPaymentRemainder='" + getIsFirstPaymentRemainder() + "'" +
            ", firstPaymentRemainderDays=" + getFirstPaymentRemainderDays() +
            ", isSecondPaymentRemainder='" + getIsSecondPaymentRemainder() + "'" +
            ", secondPaymentRemainderDays=" + getSecondPaymentRemainderDays() +
            ", isOverDuePaymentRemainder='" + getIsOverDuePaymentRemainder() + "'" +
            ", overDuePaymentRemainderAfterDueDateOrUntilPaid='" + getOverDuePaymentRemainderAfterDueDateOrUntilPaid() + "'" +
            ", overDuePaymentRemainderDays=" + getOverDuePaymentRemainderDays() +
            ", isRemainderRecipients='" + getIsRemainderRecipients() + "'" +
            ", remainderRecipients='" + getRemainderRecipients() + "'" +
            ", dueDate=" + getDueDateId() +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            "}";
    }
}
