package com.synectiks.cms.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A DTO for the Holiday entity.
 */
public class HolidayDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer srNo;

    @NotNull
    private String sHoliday;

    @NotNull
    private LocalDate aDate;

    @NotNull
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getsHoliday() {
        return sHoliday;
    }

    public void setsHoliday(String sHoliday) {
        this.sHoliday = sHoliday;
    }

    public LocalDate getaDate() {
        return aDate;
    }

    public void setaDate(LocalDate aDate) {
        this.aDate = aDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HolidayDTO holidayDTO = (HolidayDTO) o;
        if (holidayDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), holidayDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HolidayDTO{" +
            "id=" + getId() +
            ", srNo=" + getSrNo() +
            ", sHoliday='" + getsHoliday() + "'" +
            ", aDate='" + getaDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
