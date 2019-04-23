package com.synectiks.cms.web.rest;


import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsHolidayVo;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HolidayRestController {

    private final Logger logger = LoggerFactory.getLogger(HolidayRestController.class);
    private static final String ENTITY_NAME = "holiday";

    @Autowired
    private HolidayRepository holidayRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/cmsholidays")
    public ResponseEntity<CmsHolidayVo> createHoliday(@Valid @RequestBody CmsHolidayVo cmsHolidayVo) throws Exception {
        logger.debug("REST request to save an Holiday : {}", cmsHolidayVo);
        if (cmsHolidayVo.getId() != null) {
            throw new BadRequestAlertException("A new holiday cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        Holiday hd = CommonUtil.createCopyProperties(cmsHolidayVo, Holiday.class);

        hd = holidayRepository.save(hd);
        String hdDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, hd.getHolidayDate());

        cmsHolidayVo.setId(hd.getId());
        cmsHolidayVo.setStrHolidayDate(hdDt);
        return ResponseEntity.created(new URI("/api/holidays/" + cmsHolidayVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsHolidayVo.getId().toString()))
            .body(cmsHolidayVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsholidays")
    public ResponseEntity<CmsHolidayVo> updateHoliday(@Valid @RequestBody CmsHolidayVo cmsHolidayVo) throws Exception {
        logger.debug("REST request to update an Holiday : {}", cmsHolidayVo);
        if (cmsHolidayVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Holiday hd = CommonUtil.createCopyProperties(cmsHolidayVo, Holiday.class);
        hd = holidayRepository.save(hd);
        String hdDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, hd.getHolidayDate());


        cmsHolidayVo.setStrHolidayDate(hdDt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsHolidayVo.getId().toString()))
            .body(cmsHolidayVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholidays")
    public List<CmsHolidayVo> getAllHolidays() throws Exception {
        logger.debug("REST request to get all the Holidays");
        List<Holiday> list = holidayRepository.findAll();
        List<CmsHolidayVo> ls = new ArrayList<>();
        for(Holiday hd: list) {
            String hdDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, hd.getHolidayDate());
            CmsHolidayVo chd = CommonUtil.createCopyProperties(hd, CmsHolidayVo.class);
            chd.setStrHolidayDate(hdDt);
            ls.add(chd);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsholidays/{id}")
    public ResponseEntity<CmsHolidayVo> getHoliday(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an Holiday : {}", id);
        Optional<Holiday> hd = holidayRepository.findById(id);
        CmsHolidayVo chd = new CmsHolidayVo();
        if(hd.isPresent()) {
            String hdDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, hd.get().getHolidayDate());

            chd = CommonUtil.createCopyProperties(hd.get(), CmsHolidayVo.class);
            chd.setStrHolidayDate(hdDt);

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(chd));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsholidays/{id}")
    public ResponseEntity<Void> deleteHoliday(@PathVariable Long id) {
        logger.debug("REST request to delete an Holiday : {}", id);
        holidayRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
