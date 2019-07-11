package com.synectiks.cms.domain;

import com.synectiks.cms.domain.enumeration.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class CmsHolidayVo {

    private Long id;
    private String holidayDesc;
    private LocalDate holidayDate;
    private Status holidayStatus;
    private AcademicYear academicyear;

    private String strHolidayDate;

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

    public AcademicYear getAcademicyear() {
        return academicyear;
    }

    public void setAcademicyear(AcademicYear academicyear) {
        this.academicyear = academicyear;
    }

    public String getStrHolidayDate() {
        return strHolidayDate;
    }

    public void setStrHolidayDate(String strHolidayDate) {
        this.strHolidayDate = strHolidayDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmsHolidayVo that = (CmsHolidayVo) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(holidayDesc, that.holidayDesc) &&
            Objects.equals(holidayDate, that.holidayDate) &&
            holidayStatus == that.holidayStatus &&
            Objects.equals(academicyear, that.academicyear) &&
            Objects.equals(strHolidayDate, that.strHolidayDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, holidayDesc, holidayDate, holidayStatus, academicyear, strHolidayDate);
    }

    @Override
    public String toString() {
        return "CmsHolidayVo{" +
            "id=" + id +
            ", holidayDesc='" + holidayDesc + '\'' +
            ", holidayDate=" + holidayDate +
            ", holidayStatus=" + holidayStatus +
            ", academicyear=" + academicyear +
            ", strHolidayDate='" + strHolidayDate + '\'' +
            '}';
    }
}
