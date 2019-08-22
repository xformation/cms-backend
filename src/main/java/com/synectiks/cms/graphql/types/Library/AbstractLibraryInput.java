package com.synectiks.cms.graphql.types.Library;

import java.util.Objects;

public class AbstractLibraryInput {
    private Long id;
    private String bookTitle;
    private String author;
    private Long bookNo;
    private Long noOfCopies;
    private String additionalInfo;
    private Long uniqueNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getBookNo() {
        return bookNo;
    }

    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Long getUniqueNo() {
        return uniqueNo;
    }

    public void setUniqueNo(Long uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractLibraryInput)) return false;
        AbstractLibraryInput that = (AbstractLibraryInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(bookTitle, that.bookTitle) &&
            Objects.equals(author, that.author) &&
            Objects.equals(bookNo, that.bookNo) &&
            Objects.equals(noOfCopies, that.noOfCopies) &&
            Objects.equals(additionalInfo, that.additionalInfo) &&
            Objects.equals(uniqueNo, that.uniqueNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookTitle, author, bookNo, noOfCopies, additionalInfo, uniqueNo);
    }

    @Override
    public String toString() {
        return "AbstractLibraryInput{" +
            "id=" + id +
            ", bookTitle='" + bookTitle + '\'' +
            ", author='" + author + '\'' +
            ", bookNo=" + bookNo +
            ", noOfCopies=" + noOfCopies +
            ", additionalInfo='" + additionalInfo + '\'' +
            ", uniqueNo=" + uniqueNo +
            '}';
    }
}
