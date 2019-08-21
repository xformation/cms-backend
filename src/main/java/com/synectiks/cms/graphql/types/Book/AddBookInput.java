package com.synectiks.cms.graphql.types.Book;

import java.util.Objects;

public class AddBookInput extends AbstractBookInput {
    private Long studentId;
    private Long libraryId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddBookInput)) return false;
        if (!super.equals(o)) return false;
        AddBookInput that = (AddBookInput) o;
        return Objects.equals(studentId, that.studentId) &&
            Objects.equals(libraryId, that.libraryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentId, libraryId);
    }

    @Override
    public String toString() {
        return "AddBookInput{" +
            "studentId=" + studentId +
            ", libraryId=" + libraryId +
            '}';
    }
}
