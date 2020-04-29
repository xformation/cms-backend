package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.*;
//import com.synectiks.cms.filter.Book.BookListFilterInput;
import com.synectiks.cms.filter.Book.BookListFilterInput;
import com.synectiks.cms.graphql.types.Book.AddBookInput;
import com.synectiks.cms.repository.BookRepository;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    CommonService commonService;

    public List<CmsBookVo> searchBook(Long studentId,Long bookId,Long departmentId,Long batchId) {
        Book b = new Book();
        if (studentId != null) {
            Student student = new Student();
            student.setId(studentId);
            b.setStudent(student);
        }
        if (bookId != null) {
            b.setId(bookId);
        }
        if (departmentId != null) {
            b.setDepartmentId(departmentId);
        }
        if (batchId != null) {
            b.setBatchId(batchId);
        }
//        if (bookStatus != null) {
//            b.set(bookId);
//        }
        Example<Book> example = Example.of(b);
        List<Book> list = this.bookRepository.findAll(example);
        List<CmsBookVo> ls = new ArrayList<>();
        for(Book book: list) {
            CmsBookVo vo = CommonUtil.createCopyProperties(book, CmsBookVo.class);
//            vo.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(book.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setStrDueDate(DateFormatUtil.changeLocalDateFormat(book.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setStrReceivedDate(DateFormatUtil.changeLocalDateFormat(book.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setIssueDate(null);
//            vo.setDueDate(null);
//            vo.setReceivedDate(null);
            ls.add(vo);
        }
        return ls;
    }

    public List<CmsBookVo> searchBook(BookListFilterInput filter) {
        Book b = new Book();
        if (!CommonUtil.isNullOrEmpty(filter.getStudentId())) {
            Student student = this.commonService.getStudentById(Long.valueOf(filter.getStudentId()));
            if (student != null) {
                b.setStudent(student);
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBookId())) {
            if (b != null) {
                b.setId(Long.valueOf(filter.getBookId()));
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
            b.setDepartmentId(Long.parseLong(filter.getDepartmentId()));
        }
        if (!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
            b.setBatchId(Long.parseLong(filter.getBatchId()));
        }
        Example<Book> example = Example.of(b);
        List<Book> list = this.bookRepository.findAll(example);
        List<CmsBookVo> ls = new ArrayList<>();
        for(Book book: list) {
            CmsBookVo vo = CommonUtil.createCopyProperties(book, CmsBookVo.class);
//            vo.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(book.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setStrDueDate(DateFormatUtil.changeLocalDateFormat(book.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setStrReceivedDate(DateFormatUtil.changeLocalDateFormat(book.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//            vo.setIssueDate(null);
//            vo.setDueDate(null);
//            vo.setReceivedDate(null);
            ls.add(vo);
        }
        return ls;
    }

    public List<CmsBookVo> getBookList(){
        List<Book> list = this.bookRepository.findAll();
        List<CmsBookVo> ls = changeBookToCmsBookList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsBookVo getCmsBook(Long id){
        Optional<Book> b = this.bookRepository.findById(id);
        if(b.isPresent()) {
            CmsBookVo vo = CommonUtil.createCopyProperties(b.get(), CmsBookVo.class);
            convertDatesAndProvideDependencies(b.get(), vo);
            logger.debug("CmsBook for given id : "+id+". CmsBook object : "+vo);
            return vo;
        }
        logger.debug("book object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Book getBook(Long id){
        Optional< Book> b = this.bookRepository.findById(id);
        if(b.isPresent()) {
            return b.get();
        }
        logger.debug("book object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsBookVo> changeBookToCmsBookList(List<Book> list){
        List<CmsBookVo> ls = new ArrayList<>();
        for(Book b: list) {
            CmsBookVo vo = CommonUtil.createCopyProperties(b, CmsBookVo.class);
            convertDatesAndProvideDependencies(b, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Book b, CmsBookVo vo) {
        if(b.getIssueDate() != null) {
            vo.setStrIssueDate(DateFormatUtil.changeLocalDateFormat(b.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setIssueDate(null);
        }
        if(b.getDueDate() != null) {
            vo.setStrDueDate(DateFormatUtil.changeLocalDateFormat(b.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setDueDate(null);
        }
        if(b.getReceivedDate() != null) {
            vo.setStrReceivedDate(DateFormatUtil.changeLocalDateFormat(b.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setReceivedDate(null);
        }
    }

    public CmsBookVo addBook(AddBookInput input) {
        logger.info("Saving book");
        CmsBookVo vo = null;
        try {
            Book book = null;
            if (input.getId() == null) {
                logger.debug("Adding new Book");
                book = CommonUtil.createCopyProperties(input, Book.class);
            } else {
                logger.debug("Updating existing Book");
                book = this.bookRepository.findById(input.getId()).get();
            }
            Library lb = this.libraryRepository.findById(input.getLibraryId()).get();
            book.setLibrary(lb);
            Student st = this.studentRepository.findById(input.getStudentId()).get();
            book.setStudent(st);
            book.setBookStatus(input.getBookStatus());
            book.setNoOfCopiesAvailable(input.getNoOfCopiesAvailable());
            book.setBatchId(input.getBatchId());
            book.setDepartmentId(input.getDepartmentId());
            book.setIssueDate(input.getStrIssueDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            book.setDueDate(input.getStrDueDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            book.setReceivedDate(input.getStrReceivedDate() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            book = this.bookRepository.save(book);
            vo = CommonUtil.createCopyProperties(book, CmsBookVo.class);
            vo.setStrIssueDate(book.getIssueDate() != null ? DateFormatUtil.changeLocalDateFormat(book.getIssueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrDueDate(book.getDueDate() != null ? DateFormatUtil.changeLocalDateFormat(book.getDueDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrReceivedDate(book.getReceivedDate() != null ? DateFormatUtil.changeLocalDateFormat(book.getReceivedDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("book is added successfully");
                logger.debug("book is added successfully");
            } else {
                vo.setExitDescription("book is updated successfully");
                logger.debug("book is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsBookVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, book data not be saved");
            logger.error("Book save failed. Exception : ", e);
        }
        logger.info("Book saved successfully");
        List ls = getBookList();
        vo.setDataList(ls);
        return vo;
    }
}
