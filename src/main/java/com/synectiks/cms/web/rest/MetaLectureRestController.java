package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.repository.MetaLectureRepository;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing MetaLecture.
 */
@RestController
@RequestMapping("/api")
public class MetaLectureRestController {

    private final Logger logger = LoggerFactory.getLogger(MetaLectureRestController.class);

    private static final String ENTITY_NAME = "metaLecture";

    @Autowired
    private MetaLectureRepository metaLectureRepository;


    @Autowired
    private CommonService commonService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsmeta-lectures")
    public ResponseEntity<MetaLecture> createMetaLecture(@RequestBody MetaLecture metaLecture) throws URISyntaxException {
        logger.debug("REST request to save MetaLecture : {}", metaLecture);
        if (metaLecture.getId() != null) {
            throw new BadRequestAlertException("A new metaLecture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MetaLecture result = this.metaLectureRepository.save(metaLecture);
        return ResponseEntity.created(new URI("/api/meta-lectures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsmeta-lectures")
    public ResponseEntity<MetaLecture> updateMetaLecture(@RequestBody MetaLecture metaLecture) throws URISyntaxException {
        logger.debug("REST request to update MetaLecture : {}", metaLecture);
        if (metaLecture.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MetaLecture result = this.metaLectureRepository.save(metaLecture);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, metaLecture.getId().toString()))
            .body(result);
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsmeta-lectures")
    public List<MetaLecture> getAllMetaLectures() {
        logger.debug("REST request to get all MetaLectures");
        return this.metaLectureRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsmeta-lectures/{id}")
    public ResponseEntity<MetaLecture> getMetaLecture(@PathVariable Long id) {
        logger.debug("REST request to get MetaLecture : {}", id);
        Optional<MetaLecture> metaLecture = this.metaLectureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(metaLecture);
    }

    
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsmeta-lectures/{id}")
    public ResponseEntity<Void> deleteMetaLecture(@PathVariable Long id) {
        logger.debug("REST request to delete MetaLecture : {}", id);
        this.metaLectureRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsmeta-lecture-all")
    public List<MetaLecture> getAllMetaLectureRecords() {
    	return this.metaLectureRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsmeta-lecture-selected")
    public List<MetaLecture> getAllMetaLectures(@RequestParam Map<String, String> dataMap) {
        logger.debug("REST request to get all the lecture details");
        if(dataMap.containsKey("branchId") == false) {
        	logger.warn("Branch id not found. Returning empty list");
        	return Collections.emptyList();
        }else if(dataMap.containsKey("departmentId") == false) {
        	logger.warn("Department id not found. Returning empty list");
        	return Collections.emptyList();
        }
//        else if(dataMap.containsKey("subjectId") == false) {
//        	logger.warn("Subject id not found. Returning empty list");
//        	return Collections.emptyList();
//        }else if(dataMap.containsKey("teacherId") == false) {
//        	logger.warn("Teacher id not found. Returning empty list");
//        	return Collections.emptyList();
//        }
        else if(dataMap.containsKey("termId") == false) {
        	logger.warn("Term id not found. Returning empty list");
        	return Collections.emptyList();
        }else if(dataMap.containsKey("academicYear") == false) {
        	logger.warn("AcademicYear not found. Returning empty list");
        	return Collections.emptyList();
        }else if(dataMap.containsKey("sectionId") == false) {
        	logger.warn("section id not found. Returning empty list");
        	return Collections.emptyList();
        }else if(dataMap.containsKey("batchId") == false) {
        	logger.warn("batch id not found. Returning empty list");
        	return Collections.emptyList();
        }
        
        Branch branch = this.commonService.getBranchById(Long.valueOf(dataMap.get("branchId")));
        Department department = this.commonService.getDepartmentById(Long.valueOf(dataMap.get("departmentId")));
//        Subject subject = this.commonService.getSubjectById(Long.valueOf(dataMap.get("subjectId")));
//        Teacher teacher = this.commonService.getTeacherById(Long.valueOf(dataMap.get("teacherId")));
        Term term = this.commonService.getTermById(Long.valueOf(dataMap.get("termId")));
        AcademicYear academicYear = this.commonService.findAcademicYearByYear(dataMap.get("academicYear"));
        Section section = this.commonService.getSectionById(Long.valueOf(dataMap.get("sectionId")));
        Batch batch = this.commonService.getBatchById(Long.valueOf(dataMap.get("batchId")));
        
        MetaLecture metaLecture = new MetaLecture();
        metaLecture.setBranch(branch);
        metaLecture.setDepartment(department);
        metaLecture.setTerm(term);
        metaLecture.setAcademicyear(academicYear);
        metaLecture.setSection(section);
        metaLecture.setBatch(batch);
        logger.debug("Retrieving meta data of lectures");
        Example<MetaLecture> example = Example.of(metaLecture);
        
        List<MetaLecture> list = this.metaLectureRepository.findAll(example);
        return list;
        
    }
    
    
}
