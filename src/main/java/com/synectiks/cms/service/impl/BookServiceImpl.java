package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.BookService;
import com.synectiks.cms.domain.Book;
import com.synectiks.cms.repository.BookRepository;
//import com.synectiks.cms.repository.search.BookSearchRepository;
import com.synectiks.cms.service.dto.BookDTO;
import com.synectiks.cms.service.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Book.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    //private final BookSearchRepository bookSearchRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper/*, BookSearchRepository bookSearchRepository*/) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        //this.bookSearchRepository = bookSearchRepository;
    }

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BookDTO save(BookDTO bookDTO) {
        log.debug("Request to save Book : {}", bookDTO);
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        BookDTO result = bookMapper.toDto(book);
        //bookSearchRepository.save(book);
        return result;
    }

    /**
     * Get all the books.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        log.debug("Request to get all Books");
        return bookRepository.findAll().stream()
            .map(bookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one book by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookDTO> findOne(Long id) {
        log.debug("Request to get Book : {}", id);
        return bookRepository.findById(id)
            .map(bookMapper::toDto);
    }

    /**
     * Delete the book by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Book : {}", id);
        bookRepository.deleteById(id);
        //bookSearchRepository.deleteById(id);
    }

    /**
     * Search for the book corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> search(String query) {
        log.debug("Request to search Books for query {}", query);
        /*return StreamSupport
            .stream(bookSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bookMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
