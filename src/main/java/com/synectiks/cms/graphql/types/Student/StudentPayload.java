package com.synectiks.cms.graphql.types.Student;

import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Student;

public class StudentPayload {
    public StudentPayload(CmsStudentVo cmsStudentVo) {
        this.cmsStudentVo = cmsStudentVo;
    }

    private final CmsStudentVo cmsStudentVo;

    public CmsStudentVo getCmsStudentVo() {
        return cmsStudentVo;
    }


}
