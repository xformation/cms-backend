package com.synectiks.cms.graphql.types.Subject;

public class AddSubjectInput extends AbstractSubjectInput {
    private Long teacherId;
    private Long departmentId;

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "AddSubjectInput [teacherId=" + teacherId + ", departmentId=" + departmentId
				+ "]";
	}

}
