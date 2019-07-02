package com.synectiks.cms.graphql.types.AddNewBook;

public class AbstractAddNewBookInput {
    private Long id;
    private String bookTitle;
    private String author;
    private Long bookId;


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

    @Override
    public String toString() {
        return "AbstractNewBookInput{" +
            "id=" + id +
            ", bookTitle='" + bookTitle + '\'' +
            ", author='" + author + '\'' +
            ", bookId=" + bookId +
            '}';
    }
}
