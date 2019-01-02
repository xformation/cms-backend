package com.synectiks.cms.graphql.types.Subject;

public class AddSubjectInput extends AbstractSubjectInput {
    private Long studentId;
    private Long teacherId;
    private Long periodsId;

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

    public Long getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(Long periodsId) {
        this.periodsId = periodsId;
    }

    @Override
    public String toString() {
        return "AddSubjectInput{" +
            "studentId=" + studentId +
            ", teacherId=" + teacherId +
            ", periodsId=" + periodsId +
            '}'+ super.toString();
    }
}
