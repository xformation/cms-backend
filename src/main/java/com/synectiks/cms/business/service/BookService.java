package com.synectiks.cms.business.service;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.filter.Book.BookListFilterInput;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    CommonService commonService;

    public List<Library> searchBook(String bookTitle, String author, Long batchId, Long subjectId) {
        Library book = new Library();

        if (bookTitle != null) {
            book.setBookTitle(bookTitle);
        }

        if (author != null) {
            book.setAuthor(author);
        }

        if (batchId != null) {
            Batch batch = new Batch();
            batch.setId(batchId);
            book.setBatch(batch);
        }
        if (subjectId != null) {
            Subject subject = new Subject();
            subject.setId(subjectId);
            book.setSubject(subject);
        }
        Example<Library> example = Example.of(book);
        List<Library> list = this.libraryRepository.findAll(example);
        return list;
    }

    public List<Library> searchBook(BookListFilterInput filter) {
        Library book = new Library();
        if (!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
            if (batch != null) {
                book.setBatch(batch);
            }

            if (!CommonUtil.isNullOrEmpty(filter.getSubjectId())) {
                Subject subject = this.commonService.getSubjectById(Long.valueOf(filter.getSubjectId()));
                if (subject != null) {
                    book.setSubject(subject);
                }


            }
        }
        Example<Library> example = Example.of(book);
        List<Library> list = this.libraryRepository.findAll(example);
        return list;
    }
}
