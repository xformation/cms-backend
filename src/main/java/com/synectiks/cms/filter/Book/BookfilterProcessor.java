package com.synectiks.cms.filter.Book;

import com.synectiks.cms.business.service.BookService;
import com.synectiks.cms.domain.AddNewBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BookfilterProcessor  {
    @Autowired

    private  BookService bookService;
    public List<AddNewBook> searchBook(String bookTitle, String author, Long departmentId, Long batchId, Long subjectId) {
        return bookService.searchBook(bookTitle, author, departmentId, batchId, subjectId);
    }
    public List<AddNewBook> searchBook(BookListFilterInput filter){
           return bookService.searchBook(filter);
        }
    }

