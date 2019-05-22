package com.synectiks.cms.graphql.types.AcademicExamSetting;

public class UpdateAcademicExamSettingInput extends AbstractAcademicExamSettingInput{
    private Long departmentId;
    private Long academicYearId;
    private Long sectionId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
}
