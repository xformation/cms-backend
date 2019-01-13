//package com.synectiks.cms.graphql.types.Semester;
//
//import com.synectiks.cms.domain.enumeration.Sem;
//
//import java.util.Objects;
//
//public class AbstractSemesterInput {
//    private Long id;
//    private Sem sem;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Sem getSem() {
//        return sem;
//    }
//
//    public void setSem(Sem sem) {
//        this.sem = sem;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof AbstractSemesterInput)) return false;
//        AbstractSemesterInput that = (AbstractSemesterInput) o;
//        return Objects.equals(getId(), that.getId()) &&
//            getSem() == that.getSem();
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getSem());
//    }
//
//    @Override
//    public String toString() {
//        return "AbstractSemesterInput{" +
//            "id=" + id +
//            ", sem=" + sem +
//            '}';
//    }
//}
