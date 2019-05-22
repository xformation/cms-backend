package com.synectiks.cms.graphql.types.TypeOfGrading;

public class UpdateTypeOfGradingInput extends AbstractTypeOfGradingInput {
    private Long AcademicExamSettingId;

    public Long getAcademicExamSettingId() {
        return AcademicExamSettingId;
    }

    public void setAcademicExamSettingId(Long academicExamSettingId) {
        AcademicExamSettingId = academicExamSettingId;
    }
}
