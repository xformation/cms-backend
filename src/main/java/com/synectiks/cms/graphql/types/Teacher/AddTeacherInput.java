package com.synectiks.cms.graphql.types.Teacher;

public class AddTeacherInput extends AbstractTeacherInput {

    private Long periodsId;

    public Long getPeriodsId() {
        return periodsId;
    }

    public void setPeriodsId(Long periodsId) {
        this.periodsId = periodsId;
    }

    @Override
    public String toString() {
        return "AddTeacherInput{" +
            "periodsId=" + periodsId +
            '}'+ super.toString();
    }
}
