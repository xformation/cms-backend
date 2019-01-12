package com.synectiks.cms.graphql.types.Subject;

public class AddSubjectInput extends AbstractSubjectInput {
    private Long studentId;
    private Long teacherId;
    private Long departmentId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

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
		return "AddSubjectInput [studentId=" + studentId + ", teacherId=" + teacherId + ", departmentId=" + departmentId
				+ "]";
	}

    
}
