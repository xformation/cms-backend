package com.synectiks.cms.web.rest;

import static com.synectiks.cms.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import com.synectiks.cms.CmsApp;
import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.NotificationsRepository;
import com.synectiks.cms.service.NotificationsService;
import com.synectiks.cms.service.dto.NotificationsDTO;
import com.synectiks.cms.service.mapper.NotificationsMapper;
import com.synectiks.cms.web.rest.errors.ExceptionTranslator;
/**
 * Integration tests for the {@Link NotificationsResource} REST controller.
 */
@SpringBootTest(classes = CmsApp.class)
public class NotificationsResourceIT {

    private static final String DEFAULT_MESSAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.ACTIVE;
    private static final Status UPDATED_STATUS = Status.DEACTIVE;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private NotificationsMapper notificationsMapper;

    @Autowired
    private NotificationsService notificationsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restNotificationsMockMvc;

    private Notifications notifications;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationsResource notificationsResource = new NotificationsResource(notificationsService);
        this.restNotificationsMockMvc = MockMvcBuilders.standaloneSetup(notificationsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Notifications createEntity(EntityManager em) {
        Notifications notifications = new Notifications()
            .messageCode(DEFAULT_MESSAGE_CODE)
            .message(DEFAULT_MESSAGE)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON);
        return notifications;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Notifications createUpdatedEntity(EntityManager em) {
        Notifications notifications = new Notifications()
            .messageCode(UPDATED_MESSAGE_CODE)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        return notifications;
    }

    @BeforeEach
    public void initTest() {
        notifications = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotifications() throws Exception {
        int databaseSizeBeforeCreate = notificationsRepository.findAll().size();

        // Create the Notifications
        NotificationsDTO notificationsDTO = notificationsMapper.toDto(notifications);
        restNotificationsMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationsDTO)))
            .andExpect(status().isCreated());

        // Validate the Notifications in the database
        List<Notifications> notificationsList = notificationsRepository.findAll();
        assertThat(notificationsList).hasSize(databaseSizeBeforeCreate + 1);
        Notifications testNotifications = notificationsList.get(notificationsList.size() - 1);
        assertThat(testNotifications.getMessageCode()).isEqualTo(DEFAULT_MESSAGE_CODE);
        assertThat(testNotifications.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testNotifications.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testNotifications.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testNotifications.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testNotifications.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testNotifications.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createNotificationsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationsRepository.findAll().size();

        // Create the Notifications with an existing ID
        notifications.setId(1L);
        NotificationsDTO notificationsDTO = notificationsMapper.toDto(notifications);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationsMockMvc.perform(post("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifications in the database
        List<Notifications> notificationsList = notificationsRepository.findAll();
        assertThat(notificationsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNotifications() throws Exception {
        // Initialize the database
        notificationsRepository.saveAndFlush(notifications);

        // Get all the notificationsList
        restNotificationsMockMvc.perform(get("/api/notifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifications.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageCode").value(hasItem(DEFAULT_MESSAGE_CODE.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }
    
    @Test
    @Transactional
    public void getNotifications() throws Exception {
        // Initialize the database
        notificationsRepository.saveAndFlush(notifications);

        // Get the notifications
        restNotificationsMockMvc.perform(get("/api/notifications/{id}", notifications.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notifications.getId().intValue()))
            .andExpect(jsonPath("$.messageCode").value(DEFAULT_MESSAGE_CODE.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNotifications() throws Exception {
        // Get the notifications
        restNotificationsMockMvc.perform(get("/api/notifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotifications() throws Exception {
        // Initialize the database
        notificationsRepository.saveAndFlush(notifications);

        int databaseSizeBeforeUpdate = notificationsRepository.findAll().size();

        // Update the notifications
        Notifications updatedNotifications = notificationsRepository.findById(notifications.getId()).get();
        // Disconnect from session so that the updates on updatedNotifications are not directly saved in db
        em.detach(updatedNotifications);
        updatedNotifications
            .messageCode(UPDATED_MESSAGE_CODE)
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON);
        NotificationsDTO notificationsDTO = notificationsMapper.toDto(updatedNotifications);

        restNotificationsMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationsDTO)))
            .andExpect(status().isOk());

        // Validate the Notifications in the database
        List<Notifications> notificationsList = notificationsRepository.findAll();
        assertThat(notificationsList).hasSize(databaseSizeBeforeUpdate);
        Notifications testNotifications = notificationsList.get(notificationsList.size() - 1);
        assertThat(testNotifications.getMessageCode()).isEqualTo(UPDATED_MESSAGE_CODE);
        assertThat(testNotifications.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testNotifications.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testNotifications.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testNotifications.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testNotifications.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testNotifications.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingNotifications() throws Exception {
        int databaseSizeBeforeUpdate = notificationsRepository.findAll().size();

        // Create the Notifications
        NotificationsDTO notificationsDTO = notificationsMapper.toDto(notifications);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationsMockMvc.perform(put("/api/notifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifications in the database
        List<Notifications> notificationsList = notificationsRepository.findAll();
        assertThat(notificationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotifications() throws Exception {
        // Initialize the database
        notificationsRepository.saveAndFlush(notifications);

        int databaseSizeBeforeDelete = notificationsRepository.findAll().size();

        // Delete the notifications
        restNotificationsMockMvc.perform(delete("/api/notifications/{id}", notifications.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Notifications> notificationsList = notificationsRepository.findAll();
        assertThat(notificationsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchNotifications() throws Exception {
        // Initialize the database
        notificationsRepository.saveAndFlush(notifications);
        // Search the notifications
        restNotificationsMockMvc.perform(get("/api/_search/notifications?query=id:" + notifications.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifications.getId().intValue())))
            .andExpect(jsonPath("$.[*].messageCode").value(hasItem(DEFAULT_MESSAGE_CODE)))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Notifications.class);
        Notifications notifications1 = new Notifications();
        notifications1.setId(1L);
        Notifications notifications2 = new Notifications();
        notifications2.setId(notifications1.getId());
        assertThat(notifications1).isEqualTo(notifications2);
        notifications2.setId(2L);
        assertThat(notifications1).isNotEqualTo(notifications2);
        notifications1.setId(null);
        assertThat(notifications1).isNotEqualTo(notifications2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationsDTO.class);
        NotificationsDTO notificationsDTO1 = new NotificationsDTO();
        notificationsDTO1.setId(1L);
        NotificationsDTO notificationsDTO2 = new NotificationsDTO();
        assertThat(notificationsDTO1).isNotEqualTo(notificationsDTO2);
        notificationsDTO2.setId(notificationsDTO1.getId());
        assertThat(notificationsDTO1).isEqualTo(notificationsDTO2);
        notificationsDTO2.setId(2L);
        assertThat(notificationsDTO1).isNotEqualTo(notificationsDTO2);
        notificationsDTO1.setId(null);
        assertThat(notificationsDTO1).isNotEqualTo(notificationsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(notificationsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(notificationsMapper.fromId(null)).isNull();
    }
}
