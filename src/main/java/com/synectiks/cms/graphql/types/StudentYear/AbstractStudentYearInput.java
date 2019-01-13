//package com.synectiks.cms.graphql.types.StudentYear;
//
//import com.synectiks.cms.domain.enumeration.SYear;
//
//import java.util.Objects;
//
//public class AbstractStudentYearInput {
//    private Long id;
//    private SYear yearDesc;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public SYear getYearDesc() {
//        return yearDesc;
//    }
//
//    public void setYearDesc(SYear yearDesc) {
//        this.yearDesc = yearDesc;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AbstractStudentYearInput that = (AbstractStudentYearInput) o;
//        return Objects.equals(id, that.id) &&
//            yearDesc == that.yearDesc;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, yearDesc);
//    }
//
//    @Override
//    public String toString() {
//        return "AbstractStudentYearInput{" +
//            "id=" + id +
//            ", yearDesc=" + yearDesc +
//            '}';
//    }
//}
