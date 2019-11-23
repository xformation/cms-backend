package com.synectiks.cms.graphql.types.AcademicExamSetting;

import com.synectiks.commons.entities.cms.AcademicExamSetting;

import java.util.List;

public class RemoveAcademicExamSettingPayload {
    private final List<AcademicExamSetting> academicExamSettings;

    public RemoveAcademicExamSettingPayload(List<AcademicExamSetting> academicExamSettings)
    {
        this.academicExamSettings = academicExamSettings;
    }
    public List<AcademicExamSetting> getAcademicExamSettings(){
        return academicExamSettings;
    }
}
