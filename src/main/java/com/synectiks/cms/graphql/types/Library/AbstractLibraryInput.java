package com.synectiks.cms.graphql.types.Library;

public class AbstractLibraryInput {
    private Long id;
    private String bookTitle;
    private String author;
    private Long bookId;
    private Long noOfCopies;

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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(Long noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    @Override
    public String toString() {
        return "AbstractLibraryInput{" +
            "id=" + id +
            ", bookTitle='" + bookTitle + '\'' +
            ", author='" + author + '\'' +
            ", bookId=" + bookId +
            ", noOfCopies=" + noOfCopies +
            '}';
    }
}
