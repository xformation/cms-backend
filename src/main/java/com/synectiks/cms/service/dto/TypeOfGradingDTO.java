package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.synectiks.cms.domain.TypeOfGrading} entity.
 */
public class TypeOfGradingDTO implements Serializable {

    private Long id;

    private Integer minMarks;

    private Integer maxMarks;

    private String grades;

    private Long groupvalue;


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

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Long getGroupvalue() {
        return groupvalue;
    }

    public void setGroupvalue(Long groupvalue) {
        this.groupvalue = groupvalue;
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
            ", groupvalue=" + getGroupvalue() +
            "}";
    }
}
