package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.service.NotificationsService;
import com.synectiks.cms.service.dto.NotificationsDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.cms.domain.Notifications}.
 */
@RestController
@RequestMapping("/api")
public class NotificationsResource {

    private final Logger log = LoggerFactory.getLogger(NotificationsResource.class);

    private static final String ENTITY_NAME = "notifications";

    private final NotificationsService notificationsService;

    public NotificationsResource(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    /**
     * {@code POST  /notifications} : Create a new notifications.
     *
     * @param notificationsDTO the notificationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationsDTO, or with status {@code 400 (Bad Request)} if the notifications has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notifications")
    public ResponseEntity<NotificationsDTO> createNotifications(@Valid @RequestBody NotificationsDTO notificationsDTO) throws URISyntaxException {
        log.debug("REST request to save Notifications : {}", notificationsDTO);
        if (notificationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifications cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationsDTO result = notificationsService.save(notificationsDTO);
        return ResponseEntity.created(new URI("/api/notifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notifications} : Updates an existing notifications.
     *
     * @param notificationsDTO the notificationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationsDTO,
     * or with status {@code 400 (Bad Request)} if the notificationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notifications")
    public ResponseEntity<NotificationsDTO> updateNotifications(@Valid @RequestBody NotificationsDTO notificationsDTO) throws URISyntaxException {
        log.debug("REST request to update Notifications : {}", notificationsDTO);
        if (notificationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationsDTO result = notificationsService.save(notificationsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notifications} : get all the notifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notifications in body.
     */
    @GetMapping("/notifications")
    public List<NotificationsDTO> getAllNotifications() {
        log.debug("REST request to get all Notifications");
        return notificationsService.findAll();
    }

    /**
     * {@code GET  /notifications/:id} : get the "id" notifications.
     *
     * @param id the id of the notificationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notifications/{id}")
    public ResponseEntity<NotificationsDTO> getNotifications(@PathVariable Long id) {
        log.debug("REST request to get Notifications : {}", id);
        Optional<NotificationsDTO> notificationsDTO = notificationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationsDTO);
    }

    /**
     * {@code DELETE  /notifications/:id} : delete the "id" notifications.
     *
     * @param id the id of the notificationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<Void> deleteNotifications(@PathVariable Long id) {
        log.debug("REST request to delete Notifications : {}", id);
        notificationsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/notifications?query=:query} : search for the notifications corresponding
     * to the query.
     *
     * @param query the query of the notifications search.
     * @return the result of the search.
     */
    @GetMapping("/_search/notifications")
    public List<NotificationsDTO> searchNotifications(@RequestParam String query) {
        log.debug("REST request to search Notifications for query {}", query);
        return notificationsService.search(query);
    }

}
