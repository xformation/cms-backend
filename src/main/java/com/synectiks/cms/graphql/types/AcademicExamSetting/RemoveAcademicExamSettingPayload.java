package com.synectiks.cms.graphql.types.AcademicExamSetting;

import java.util.List;

import com.synectiks.cms.entities.AcademicExamSetting;

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
