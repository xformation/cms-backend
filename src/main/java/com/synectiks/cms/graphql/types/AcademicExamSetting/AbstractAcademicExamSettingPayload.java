package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.cms.entities.AcademicExamSetting;

public class AbstractAcademicExamSettingPayload {
    private final AcademicExamSetting academicExamSetting;

    public AbstractAcademicExamSettingPayload(AcademicExamSetting academicExamSetting) {
        this.academicExamSetting = academicExamSetting;
    }

    public AcademicExamSetting getAcademicExamSetting() {
        return academicExamSetting;
    }
}
