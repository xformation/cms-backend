package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A DTO for the PaymentRemainder entity.
 */
public class PaymentRemainderDTO implements Serializable {

    private Long id;

    @NotNull
    private Status feeRemainder;

    @NotNull
    private Integer noticeDay;

    @NotNull
    private Status overDueRemainder;

    @NotNull
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

    public Status getFeeRemainder() {
        return feeRemainder;
    }

    public void setFeeRemainder(Status feeRemainder) {
        this.feeRemainder = feeRemainder;
    }

    public Integer getNoticeDay() {
        return noticeDay;
    }

    public void setNoticeDay(Integer noticeDay) {
        this.noticeDay = noticeDay;
    }

    public Status getOverDueRemainder() {
        return overDueRemainder;
    }

    public void setOverDueRemainder(Status overDueRemainder) {
        this.overDueRemainder = overDueRemainder;
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
            ", feeRemainder='" + getFeeRemainder() + "'" +
            ", noticeDay=" + getNoticeDay() +
            ", overDueRemainder='" + getOverDueRemainder() + "'" +
            ", remainderRecipients='" + getRemainderRecipients() + "'" +
            ", dueDate=" + getDueDateId() +
            ", college=" + getCollegeId() +
            ", branch=" + getBranchId() +
            "}";
    }
}
