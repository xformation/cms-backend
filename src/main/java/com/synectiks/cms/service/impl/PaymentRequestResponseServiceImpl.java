package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.PaymentRequestResponseService;
import com.synectiks.commons.entities.cms.PaymentRequestResponse;
import com.synectiks.cms.repository.PaymentRequestResponseRepository;
//import com.synectiks.cms.repository.search.PaymentRequestResponseSearchRepository;
import com.synectiks.cms.service.dto.PaymentRequestResponseDTO;
import com.synectiks.cms.service.mapper.PaymentRequestResponseMapper;
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
 * Service Implementation for managing {@link PaymentRequestResponse}.
 */
@Service
@Transactional
public class PaymentRequestResponseServiceImpl implements PaymentRequestResponseService {

    private final Logger log = LoggerFactory.getLogger(PaymentRequestResponseServiceImpl.class);

    private final PaymentRequestResponseRepository paymentRequestResponseRepository;

    private final PaymentRequestResponseMapper paymentRequestResponseMapper;

    //private final PaymentRequestResponseSearchRepository paymentRequestResponseSearchRepository;

    public PaymentRequestResponseServiceImpl(PaymentRequestResponseRepository paymentRequestResponseRepository, PaymentRequestResponseMapper paymentRequestResponseMapper/*, PaymentRequestResponseSearchRepository paymentRequestResponseSearchRepository*/) {
        this.paymentRequestResponseRepository = paymentRequestResponseRepository;
        this.paymentRequestResponseMapper = paymentRequestResponseMapper;
        //this.paymentRequestResponseSearchRepository = paymentRequestResponseSearchRepository;
    }

    /**
     * Save a paymentRequestResponse.
     *
     * @param paymentRequestResponseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaymentRequestResponseDTO save(PaymentRequestResponseDTO paymentRequestResponseDTO) {
        log.debug("Request to save PaymentRequestResponse : {}", paymentRequestResponseDTO);
        PaymentRequestResponse paymentRequestResponse = paymentRequestResponseMapper.toEntity(paymentRequestResponseDTO);
        paymentRequestResponse = paymentRequestResponseRepository.save(paymentRequestResponse);
        PaymentRequestResponseDTO result = paymentRequestResponseMapper.toDto(paymentRequestResponse);
        //paymentRequestResponseSearchRepository.save(paymentRequestResponse);
        return result;
    }

    /**
     * Get all the paymentRequestResponses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PaymentRequestResponseDTO> findAll() {
        log.debug("Request to get all PaymentRequestResponses");
        return paymentRequestResponseRepository.findAll().stream()
            .map(paymentRequestResponseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one paymentRequestResponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentRequestResponseDTO> findOne(Long id) {
        log.debug("Request to get PaymentRequestResponse : {}", id);
        return paymentRequestResponseRepository.findById(id)
            .map(paymentRequestResponseMapper::toDto);
    }

    /**
     * Delete the paymentRequestResponse by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentRequestResponse : {}", id);
        paymentRequestResponseRepository.deleteById(id);
        //paymentRequestResponseSearchRepository.deleteById(id);
    }

    /**
     * Search for the paymentRequestResponse corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<PaymentRequestResponseDTO> search(String query) {
        log.debug("Request to search PaymentRequestResponses for query {}", query);
        /*return StreamSupport
            .stream(paymentRequestResponseSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(paymentRequestResponseMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
