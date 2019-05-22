package com.synectiks.cms.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.GradesEnum;

/**
 * A DTO for the TypeOfGrading entity.
 */
public class TypeOfGradingDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer minMarks;

    @NotNull
    private Integer maxMarks;

    @NotNull
    private GradesEnum grades;


    private Long academicExamSettingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMinMarks() {
        return minMarks;
    }

    public void setMinMarks(Integer minMarks) {
        this.minMarks = minMarks;
    }

    public Integer getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(Integer maxMarks) {
        this.maxMarks = maxMarks;
    }

    public GradesEnum getGrades() {
        return grades;
    }

    public void setGrades(GradesEnum grades) {
        this.grades = grades;
    }

    public Long getAcademicExamSettingId() {
        return academicExamSettingId;
    }

    public void setAcademicExamSettingId(Long academicExamSettingId) {
        this.academicExamSettingId = academicExamSettingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeOfGradingDTO typeOfGradingDTO = (TypeOfGradingDTO) o;
        if (typeOfGradingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeOfGradingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeOfGradingDTO{" +
            "id=" + getId() +
            ", minMarks=" + getMinMarks() +
            ", maxMarks=" + getMaxMarks() +
            ", grades='" + getGrades() + "'" +
            ", academicExamSetting=" + getAcademicExamSettingId() +
            "}";
    }
}
