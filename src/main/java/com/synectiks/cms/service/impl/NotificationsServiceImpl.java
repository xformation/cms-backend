package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.NotificationsService;
import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.repository.NotificationsRepository;
import com.synectiks.cms.repository.search.NotificationsSearchRepository;
import com.synectiks.cms.service.dto.NotificationsDTO;
import com.synectiks.cms.service.mapper.NotificationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Notifications}.
 */
@Service
@Transactional
public class NotificationsServiceImpl implements NotificationsService {

    private final Logger log = LoggerFactory.getLogger(NotificationsServiceImpl.class);

    private final NotificationsRepository notificationsRepository;

    private final NotificationsMapper notificationsMapper;

    private final NotificationsSearchRepository notificationsSearchRepository;

    public NotificationsServiceImpl(NotificationsRepository notificationsRepository, NotificationsMapper notificationsMapper, NotificationsSearchRepository notificationsSearchRepository) {
        this.notificationsRepository = notificationsRepository;
        this.notificationsMapper = notificationsMapper;
        this.notificationsSearchRepository = notificationsSearchRepository;
    }

    /**
     * Save a notifications.
     *
     * @param notificationsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public NotificationsDTO save(NotificationsDTO notificationsDTO) {
        log.debug("Request to save Notifications : {}", notificationsDTO);
        Notifications notifications = notificationsMapper.toEntity(notificationsDTO);
        notifications = notificationsRepository.save(notifications);
        NotificationsDTO result = notificationsMapper.toDto(notifications);
        notificationsSearchRepository.save(notifications);
        return result;
    }

    /**
     * Get all the notifications.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationsDTO> findAll() {
        log.debug("Request to get all Notifications");
        return notificationsRepository.findAll().stream()
            .map(notificationsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notifications by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationsDTO> findOne(Long id) {
        log.debug("Request to get Notifications : {}", id);
        return notificationsRepository.findById(id)
            .map(notificationsMapper::toDto);
    }

    /**
     * Delete the notifications by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Notifications : {}", id);
        notificationsRepository.deleteById(id);
        notificationsSearchRepository.deleteById(id);
    }

    /**
     * Search for the notifications corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationsDTO> search(String query) {
        log.debug("Request to search Notifications for query {}", query);
        return StreamSupport
            .stream(notificationsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(notificationsMapper::toDto)
            .collect(Collectors.toList());
    }
}
