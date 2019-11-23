package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.commons.entities.cms.AcademicExamSetting;

public class AbstractAcademicExamSettingPayload {
    private final AcademicExamSetting academicExamSetting;

    public AbstractAcademicExamSettingPayload(AcademicExamSetting academicExamSetting) {
        this.academicExamSetting = academicExamSetting;
    }

    public AcademicExamSetting getAcademicExamSetting() {
        return academicExamSetting;
    }
}
