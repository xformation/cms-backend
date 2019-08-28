package com.synectiks.cms.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.cms.domain.ExceptionRecord} entity.
 */
public class ExceptionRecordDTO implements Serializable {

    private Long id;

    private String exceptionSource;

    private String exceptionType;

    private String exceptionRecord;

    private LocalDate exceptionDate;

    private String user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExceptionSource() {
        return exceptionSource;
    }

    public void setExceptionSource(String exceptionSource) {
        this.exceptionSource = exceptionSource;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionRecord() {
        return exceptionRecord;
    }

    public void setExceptionRecord(String exceptionRecord) {
        this.exceptionRecord = exceptionRecord;
    }

    public LocalDate getExceptionDate() {
        return exceptionDate;
    }

    public void setExceptionDate(LocalDate exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExceptionRecordDTO exceptionRecordDTO = (ExceptionRecordDTO) o;
        if (exceptionRecordDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exceptionRecordDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExceptionRecordDTO{" +
            "id=" + getId() +
            ", exceptionSource='" + getExceptionSource() + "'" +
            ", exceptionType='" + getExceptionType() + "'" +
            ", exceptionRecord='" + getExceptionRecord() + "'" +
            ", exceptionDate='" + getExceptionDate() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}
