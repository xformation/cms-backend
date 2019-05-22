package com.synectiks.cms.graphql.types.TypeOfGrading;

import java.util.Objects;

public class AddTypeOfGradingInput extends AbstractTypeOfGradingInput {
    private  Long AcademicExamSettingId;

    public Long getAcademicExamSettingId() {
        return AcademicExamSettingId;
    }

    public void setAcademicExamSettingId(Long academicExamSettingId) {
        AcademicExamSettingId = academicExamSettingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AddTypeOfGradingInput that = (AddTypeOfGradingInput) o;
        return Objects.equals(AcademicExamSettingId, that.AcademicExamSettingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), AcademicExamSettingId);
    }

    @Override
    public String toString() {
        return "AddTypeOfGradingInput{" +
            "AcademicExamSettingId=" + AcademicExamSettingId +
            '}';
    }
}
