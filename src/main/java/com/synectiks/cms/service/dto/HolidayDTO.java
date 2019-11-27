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
    private String holidayDesc;

    @NotNull
    private LocalDate holidayDate;

    @NotNull
    private Status holidayStatus;


    private Long academicyearId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public Status getHolidayStatus() {
        return holidayStatus;
    }

    public void setHolidayStatus(Status holidayStatus) {
        this.holidayStatus = holidayStatus;
    }

    public Long getAcademicyearId() {
        return academicyearId;
    }

    public void setAcademicyearId(Long academicYearId) {
        this.academicyearId = academicYearId;
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
            ", holidayDesc='" + getHolidayDesc() + "'" +
            ", holidayDate='" + getHolidayDate() + "'" +
            ", holidayStatus='" + getHolidayStatus() + "'" +
            ", academicyear=" + getAcademicyearId() +
            "}";
    }
}
