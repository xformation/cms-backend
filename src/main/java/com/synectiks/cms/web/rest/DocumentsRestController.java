package com.synectiks.cms.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.Documents;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.repository.DocumentsRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class DocumentsRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ENTITY_NAME = "document";

    @Autowired
    private DocumentsRepository documentsRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsdocuments")
    public ResponseEntity<Documents> createDocuments(@RequestBody Documents documents, @RequestParam Map<String, String> dataMap) throws URISyntaxException {
        logger.info("REST request to create a new documents.", documents);
        if (documents.getId() != null) {
            throw new BadRequestAlertException("A new documents cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        Documents doc = CommonUtil.createCopyProperties(documents, Documents.class);
        String studentId = dataMap.get("studentId") != null ? dataMap.get("studentId").trim() : null;
        if(!CommonUtil.isNullOrEmpty(studentId)) {
        	Optional<Student> os = studentRepository.findById(Long.parseLong(studentId));
        	if(os.isPresent()) {
        		doc.setStudent(os.get());
        	}else {
        		logger.warn("Student not found for the given student id "+studentId+". Returning.");
            	return ResponseEntity.unprocessableEntity().body(documents);
        	}
        }else {
    		logger.warn("Student id is null. Returning.");
        	return ResponseEntity.unprocessableEntity().body(documents);
    	}
         
        doc.setDocumentName(documents.getDocumentName());
        if(CmsConstants.YES.equalsIgnoreCase(documents.getIsMsOneDriveStorage())){
        	doc.setIsMsOneDriveStorage(documents.getIsMsOneDriveStorage());
        	doc.setOneDrivePath(documents.getOneDrivePath());
        }else if(CmsConstants.YES.equalsIgnoreCase(documents.getIsOakStorage())){
        	doc.setIsOakStorage(documents.getIsOakStorage());
        	doc.setOakPath(documents.getOakPath());
        }else if(CmsConstants.YES.equalsIgnoreCase(documents.getIsAwsStorage())){
        	doc.setIsAwsStorage(documents.getIsAwsStorage());
        	doc.setAwsPath(documents.getAwsPath());
        }else if(CmsConstants.YES.equalsIgnoreCase(documents.getIsFlatFileStorage())) {
        	doc.setIsFlatFileStorage(documents.getIsFlatFileStorage());
            doc.setDocumentFilePath(documents.getDocumentFilePath());
        }else {
        	logger.warn("No document path specified. Returning.");
        	return ResponseEntity.unprocessableEntity().body(documents);
        }
        
        Documents result = documentsRepository.save(doc);
        documents.setId(result.getId());
        return ResponseEntity.created(new URI("/api/cmsdocuments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(documents);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsdocuments")
    public ResponseEntity<Documents> updateDocuments(@Valid @RequestBody Documents documents) throws URISyntaxException {
        logger.info("REST request to update existing documents.", documents);
        if (documents.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Documents doc = new Documents();
        doc.setDocumentName(documents.getDocumentName());
        doc.setDocumentFilePath(documents.getDocumentFilePath());
        doc.setId(documents.getId());
        Documents result = documentsRepository.save(doc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documents.getId().toString()))
            .body(documents);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdocuments")
    public List<Documents> getAllDocuments() {
        logger.debug("REST request to get all the documents.");
        List<Documents> list = documentsRepository.findAll();
        List<Documents> ls = new ArrayList<>();
        for(Documents doc : list) {
            Documents vo = new Documents();
            vo.setDocumentName(doc.getDocumentName());
            vo.setDocumentFilePath(doc.getDocumentFilePath());
            vo.setId(doc.getId());
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsdocuments/{id}")
    public List<Documents> getAllDocuments(@PathVariable Long id){
        Documents documents = new Documents();
        Example<Documents> example = Example.of(documents);
        List<Documents> list = documentsRepository.findAll(example);
        List<Documents> ls = new ArrayList<>();
        for(Documents doc : list) {
            Documents vo = CommonUtil.createCopyProperties(doc, Documents.class);
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsdocuments/{id}")
    public Integer deleteDocuments(@PathVariable Long id) {
        try {
            logger.debug("REST request to delete a Documents : {}", id);
            documentsRepository.deleteById(id);
//            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();

    }
}
