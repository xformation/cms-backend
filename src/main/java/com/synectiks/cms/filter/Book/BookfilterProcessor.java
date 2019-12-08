package com.synectiks.cms.filter.Book;

import com.synectiks.cms.business.service.BookService;
import com.synectiks.cms.entities.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookfilterProcessor  {
    @Autowired

    private  BookService bookService;
    public List<Library> searchBook(String bookTitle, String author,  Long batchId, Long subjectId) {
        return bookService.searchBook(bookTitle, author, batchId, subjectId);
    }
    public List<Library> searchBook(BookListFilterInput filter){
           return bookService.searchBook(filter);
        }
    }

