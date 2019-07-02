package com.synectiks.cms.web.rest;

import com.synectiks.cms.CmsApp;

import com.synectiks.cms.domain.AddNewBook;
import com.synectiks.cms.repository.AddNewBookRepository;
import com.synectiks.cms.repository.search.AddNewBookSearchRepository;
import com.synectiks.cms.service.AddNewBookService;
import com.synectiks.cms.service.dto.AddNewBookDTO;
import com.synectiks.cms.service.mapper.AddNewBookMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AddNewBookResource REST controller.
 *
 * @see AddNewBookResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApp.class)
public class AddNewBookResourceIntTest {

    private static final String DEFAULT_BOOK_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_BOOK_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final Long UPDATED_BOOK_ID = 2L;

    @Autowired
    private AddNewBookRepository addNewBookRepository;

    @Autowired
    private AddNewBookMapper addNewBookMapper;

    @Autowired
    private AddNewBookService addNewBookService;

    /**
     * This repository is mocked in the com.synectiks.cms.repository.search test package.
     *
     * @see com.synectiks.cms.repository.search.AddNewBookSearchRepositoryMockConfiguration
     */
    @Autowired
    private AddNewBookSearchRepository mockAddNewBookSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAddNewBookMockMvc;

    private AddNewBook addNewBook;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddNewBookResource addNewBookResource = new AddNewBookResource(addNewBookService);
        this.restAddNewBookMockMvc = MockMvcBuilders.standaloneSetup(addNewBookResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddNewBook createEntity(EntityManager em) {
        AddNewBook addNewBook = new AddNewBook()
            .bookTitle(DEFAULT_BOOK_TITLE)
            .author(DEFAULT_AUTHOR)
            .bookId(DEFAULT_BOOK_ID);
        return addNewBook;
    }

    @Before
    public void initTest() {
        addNewBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddNewBook() throws Exception {
        int databaseSizeBeforeCreate = addNewBookRepository.findAll().size();

        // Create the AddNewBook
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);
        restAddNewBookMockMvc.perform(post("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isCreated());

        // Validate the AddNewBook in the database
        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeCreate + 1);
        AddNewBook testAddNewBook = addNewBookList.get(addNewBookList.size() - 1);
        assertThat(testAddNewBook.getBookTitle()).isEqualTo(DEFAULT_BOOK_TITLE);
        assertThat(testAddNewBook.getAuthor()).isEqualTo(DEFAULT_AUTHOR);
        assertThat(testAddNewBook.getBookId()).isEqualTo(DEFAULT_BOOK_ID);

        // Validate the AddNewBook in Elasticsearch
        verify(mockAddNewBookSearchRepository, times(1)).save(testAddNewBook);
    }

    @Test
    @Transactional
    public void createAddNewBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addNewBookRepository.findAll().size();

        // Create the AddNewBook with an existing ID
        addNewBook.setId(1L);
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddNewBookMockMvc.perform(post("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddNewBook in the database
        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeCreate);

        // Validate the AddNewBook in Elasticsearch
        verify(mockAddNewBookSearchRepository, times(0)).save(addNewBook);
    }

    @Test
    @Transactional
    public void checkBookTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = addNewBookRepository.findAll().size();
        // set the field null
        addNewBook.setBookTitle(null);

        // Create the AddNewBook, which fails.
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);

        restAddNewBookMockMvc.perform(post("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isBadRequest());

        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAuthorIsRequired() throws Exception {
        int databaseSizeBeforeTest = addNewBookRepository.findAll().size();
        // set the field null
        addNewBook.setAuthor(null);

        // Create the AddNewBook, which fails.
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);

        restAddNewBookMockMvc.perform(post("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isBadRequest());

        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBookIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = addNewBookRepository.findAll().size();
        // set the field null
        addNewBook.setBookId(null);

        // Create the AddNewBook, which fails.
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);

        restAddNewBookMockMvc.perform(post("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isBadRequest());

        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAddNewBooks() throws Exception {
        // Initialize the database
        addNewBookRepository.saveAndFlush(addNewBook);

        // Get all the addNewBookList
        restAddNewBookMockMvc.perform(get("/api/add-new-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addNewBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookTitle").value(hasItem(DEFAULT_BOOK_TITLE.toString())))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR.toString())))
            .andExpect(jsonPath("$.[*].bookId").value(hasItem(DEFAULT_BOOK_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAddNewBook() throws Exception {
        // Initialize the database
        addNewBookRepository.saveAndFlush(addNewBook);

        // Get the addNewBook
        restAddNewBookMockMvc.perform(get("/api/add-new-books/{id}", addNewBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addNewBook.getId().intValue()))
            .andExpect(jsonPath("$.bookTitle").value(DEFAULT_BOOK_TITLE.toString()))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR.toString()))
            .andExpect(jsonPath("$.bookId").value(DEFAULT_BOOK_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAddNewBook() throws Exception {
        // Get the addNewBook
        restAddNewBookMockMvc.perform(get("/api/add-new-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddNewBook() throws Exception {
        // Initialize the database
        addNewBookRepository.saveAndFlush(addNewBook);

        int databaseSizeBeforeUpdate = addNewBookRepository.findAll().size();

        // Update the addNewBook
        AddNewBook updatedAddNewBook = addNewBookRepository.findById(addNewBook.getId()).get();
        // Disconnect from session so that the updates on updatedAddNewBook are not directly saved in db
        em.detach(updatedAddNewBook);
        updatedAddNewBook
            .bookTitle(UPDATED_BOOK_TITLE)
            .author(UPDATED_AUTHOR)
            .bookId(UPDATED_BOOK_ID);
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(updatedAddNewBook);

        restAddNewBookMockMvc.perform(put("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isOk());

        // Validate the AddNewBook in the database
        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeUpdate);
        AddNewBook testAddNewBook = addNewBookList.get(addNewBookList.size() - 1);
        assertThat(testAddNewBook.getBookTitle()).isEqualTo(UPDATED_BOOK_TITLE);
        assertThat(testAddNewBook.getAuthor()).isEqualTo(UPDATED_AUTHOR);
        assertThat(testAddNewBook.getBookId()).isEqualTo(UPDATED_BOOK_ID);

        // Validate the AddNewBook in Elasticsearch
        verify(mockAddNewBookSearchRepository, times(1)).save(testAddNewBook);
    }

    @Test
    @Transactional
    public void updateNonExistingAddNewBook() throws Exception {
        int databaseSizeBeforeUpdate = addNewBookRepository.findAll().size();

        // Create the AddNewBook
        AddNewBookDTO addNewBookDTO = addNewBookMapper.toDto(addNewBook);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddNewBookMockMvc.perform(put("/api/add-new-books")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addNewBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddNewBook in the database
        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeUpdate);

        // Validate the AddNewBook in Elasticsearch
        verify(mockAddNewBookSearchRepository, times(0)).save(addNewBook);
    }

    @Test
    @Transactional
    public void deleteAddNewBook() throws Exception {
        // Initialize the database
        addNewBookRepository.saveAndFlush(addNewBook);

        int databaseSizeBeforeDelete = addNewBookRepository.findAll().size();

        // Get the addNewBook
        restAddNewBookMockMvc.perform(delete("/api/add-new-books/{id}", addNewBook.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AddNewBook> addNewBookList = addNewBookRepository.findAll();
        assertThat(addNewBookList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the AddNewBook in Elasticsearch
        verify(mockAddNewBookSearchRepository, times(1)).deleteById(addNewBook.getId());
    }

    @Test
    @Transactional
    public void searchAddNewBook() throws Exception {
        // Initialize the database
        addNewBookRepository.saveAndFlush(addNewBook);
        when(mockAddNewBookSearchRepository.search(queryStringQuery("id:" + addNewBook.getId())))
            .thenReturn(Collections.singletonList(addNewBook));
        // Search the addNewBook
        restAddNewBookMockMvc.perform(get("/api/_search/add-new-books?query=id:" + addNewBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addNewBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].bookTitle").value(hasItem(DEFAULT_BOOK_TITLE)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].bookId").value(hasItem(DEFAULT_BOOK_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddNewBook.class);
        AddNewBook addNewBook1 = new AddNewBook();
        addNewBook1.setId(1L);
        AddNewBook addNewBook2 = new AddNewBook();
        addNewBook2.setId(addNewBook1.getId());
        assertThat(addNewBook1).isEqualTo(addNewBook2);
        addNewBook2.setId(2L);
        assertThat(addNewBook1).isNotEqualTo(addNewBook2);
        addNewBook1.setId(null);
        assertThat(addNewBook1).isNotEqualTo(addNewBook2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddNewBookDTO.class);
        AddNewBookDTO addNewBookDTO1 = new AddNewBookDTO();
        addNewBookDTO1.setId(1L);
        AddNewBookDTO addNewBookDTO2 = new AddNewBookDTO();
        assertThat(addNewBookDTO1).isNotEqualTo(addNewBookDTO2);
        addNewBookDTO2.setId(addNewBookDTO1.getId());
        assertThat(addNewBookDTO1).isEqualTo(addNewBookDTO2);
        addNewBookDTO2.setId(2L);
        assertThat(addNewBookDTO1).isNotEqualTo(addNewBookDTO2);
        addNewBookDTO1.setId(null);
        assertThat(addNewBookDTO1).isNotEqualTo(addNewBookDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(addNewBookMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(addNewBookMapper.fromId(null)).isNull();
    }
}
