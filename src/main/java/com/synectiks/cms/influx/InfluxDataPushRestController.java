package com.synectiks.cms.influx;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Influx data push.
 */
@RestController
@RequestMapping("/api")
public class InfluxDataPushRestController {

    private final Logger logger = LoggerFactory.getLogger(InfluxDataPushRestController.class);
    
    @Autowired
    private StudentAttendanceInfluxPush studentAttendanceInfluxPush;

    @Autowired
    private StudentInfluxPush studentInfluxPush;
    
    @Autowired
    private TeacherInfluxPush teacherInfluxPush;
    
    @Autowired
    private AdmissionApplicationInfluxPush admissionApplicationInfluxPush;
    
    @Autowired
    private AdmissionEnquiryInfluxPush admissionEnquiryInfluxPush;
    
    @Autowired
    private FeeInfluxPush feeInfluxPush;

    @Autowired
    private ParentInfluxPush parentInfluxPush;
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsinfluxpush/{entity}")
    public ResponseEntity<Void> createStudentAttendance(@PathVariable String entity) throws ParseException, Exception {
        logger.debug("REST request to push data in influx db for table : "+entity);
        if (entity == null) {
            throw new BadRequestAlertException("Null entity is not allowed", entity, "null entity");
        }
        InfluxPush influx = getEnity(entity) ;
        if(influx == null) {
        	throw new BadRequestAlertException("Entity not found", entity, "entity not found");
        }
        influx.pushData();
        
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(entity, "")).build();
    }

    private InfluxPush getEnity(String entity) {
    	if("student_attendance".equalsIgnoreCase(entity)) {
        	return this.studentAttendanceInfluxPush;
        }else if("student".equalsIgnoreCase(entity)) {
        	return this.studentInfluxPush;
        }else if("teacher".equalsIgnoreCase(entity)) {
        	return this.teacherInfluxPush;
        }else if("admission_application".equalsIgnoreCase(entity)) {
        	return this.admissionApplicationInfluxPush;
        }else if("fee".equalsIgnoreCase(entity)) {
        	return this.feeInfluxPush;
        }else if("admission_enquiry".equalsIgnoreCase(entity)) {
        	return this.admissionEnquiryInfluxPush;
        }else if("parent".equalsIgnoreCase(entity)){
    	    return this.parentInfluxPush;
        }
    	return null;
    }
}
