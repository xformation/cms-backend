package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StudentFee entity.
 */
public class StudentFeeDTO implements Serializable {

    private Long id;

    @NotNull
    private String totalFee;

    @NotNull
    private String feesPaid;

    @NotNull
    private String feesDue;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private String totalRemaining;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(String feesPaid) {
        this.feesPaid = feesPaid;
    }

    public String getFeesDue() {
        return feesDue;
    }

    public void setFeesDue(String feesDue) {
        this.feesDue = feesDue;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTotalRemaining() {
        return totalRemaining;
    }

    public void setTotalRemaining(String totalRemaining) {
        this.totalRemaining = totalRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentFeeDTO studentFeeDTO = (StudentFeeDTO) o;
        if (studentFeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentFeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentFeeDTO{" +
            "id=" + getId() +
            ", totalFee='" + getTotalFee() + "'" +
            ", feesPaid='" + getFeesPaid() + "'" +
            ", feesDue='" + getFeesDue() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", totalRemaining='" + getTotalRemaining() + "'" +
            "}";
    }
}
