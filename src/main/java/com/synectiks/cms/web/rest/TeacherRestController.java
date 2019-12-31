package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.CmsTeacherVo;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Teacher.
 */
@RestController
@RequestMapping("/api")
public class TeacherRestController {

    private final Logger log = LoggerFactory.getLogger(TeacherRestController.class);

    private static final String ENTITY_NAME = "teacher";

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CommonService commonService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsteachers")
    public ResponseEntity<CmsTeacherVo> createTeacher(@Valid @RequestBody CmsTeacherVo cmsTeacherVo) throws URISyntaxException {
        log.debug("REST request to save a teacher : {}", cmsTeacherVo);
        if (cmsTeacherVo.getId() != null) {
            throw new BadRequestAlertException("A new teacher cannot already have an ID which already exists", ENTITY_NAME, "idexists");
        }
        Teacher th = CommonUtil.createCopyProperties(cmsTeacherVo, Teacher.class);
        if(cmsTeacherVo.getDepartmentId() != null) {
        	Optional<Department> dt = this.departmentRepository.findById(cmsTeacherVo.getDepartmentId());
        	if(dt.isPresent()) {
        		th.setDepartment(dt.get());
        	}
        }
        if(cmsTeacherVo.getBranchId() != null) {
        	Optional<Branch> bt = this.branchRepository.findById(cmsTeacherVo.getBranchId());
        	if(bt.isPresent()) {
        		th.setBranch(bt.get());
        	}
        }
        th = this.teacherRepository.save(th);
        cmsTeacherVo = CommonUtil.createCopyProperties(th, CmsTeacherVo.class);
        
        return ResponseEntity.created(new URI("/api/teachers/" + cmsTeacherVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsTeacherVo.getId().toString()))
            .body(cmsTeacherVo);
    }

    
    @RequestMapping(method = RequestMethod.PUT, value = "/cmsteachers")
    public ResponseEntity<CmsTeacherVo> updateTeacher(@Valid @RequestBody CmsTeacherVo cmsTeacherVo) throws URISyntaxException {
        log.debug("REST request to update a teacher : {}", cmsTeacherVo);
        if (cmsTeacherVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Teacher th = CommonUtil.createCopyProperties(cmsTeacherVo, Teacher.class);
        if(cmsTeacherVo.getDepartmentId() != null) {
        	Optional<Department> dt = this.departmentRepository.findById(cmsTeacherVo.getDepartmentId());
        	if(dt.isPresent()) {
        		th.setDepartment(dt.get());
        	}
        }
        if(cmsTeacherVo.getBranchId() != null) {
        	Optional<Branch> bt = this.branchRepository.findById(cmsTeacherVo.getBranchId());
        	if(bt.isPresent()) {
        		th.setBranch(bt.get());
        	}
        }
        th = this.teacherRepository.save(th);
        cmsTeacherVo = CommonUtil.createCopyProperties(th, CmsTeacherVo.class);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsTeacherVo.getId().toString()))
            .body(cmsTeacherVo);
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteachers")
    public List<CmsTeacherVo> getAllTeachers(@RequestParam Map<String, String> dataMap) {
    	List<Teacher> list =  null;
        List<CmsTeacherVo> ls = null;
    	
        if(!CommonUtil.isNullOrEmpty(dataMap.get("teacherName"))) {
        	String name = dataMap.get("teacherName");
        	ls = getTeacherListByName(name) ;
        }else {
        	log.debug("REST request to get all the Teachers");
        	list =  this.teacherRepository.findAll();
            ls = new ArrayList<>();
            for(Teacher th: list) {
            	CmsTeacherVo vo = CommonUtil.createCopyProperties(th, CmsTeacherVo.class);
            	ls.add(vo);
            }
        }
        
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsteachers-qryprms")
    public List<CmsTeacherVo> getAllTeachersByDeptBranchId(@RequestParam Map<String, String> dataMap) {
        log.debug("REST request to get all the Teachers");
        Teacher th = new Teacher();
    	
		if (dataMap.containsKey("deptId")) {
			String deptId = dataMap.get("deptId");
			Department dt = commonService.getDepartmentById(Long.parseLong(deptId));
			th.setDepartment(dt);
		}
		if (dataMap.containsKey("branchId")) {
			String branchId = dataMap.get("branchId");
			Optional<Branch> bh = this.branchRepository.findById(Long.parseLong(branchId));
			if(bh.isPresent()) {
				th.setBranch(bh.get());
			}
		}
		
		List<Teacher> list = null;
		if(dataMap.size() > 0) {
			Example<Teacher> example = Example.of(th);
			list = this.teacherRepository.findAll(example);
		}else {
			list = Collections.emptyList();
		}
		
		List<CmsTeacherVo> ls = new ArrayList<>();
        for(Teacher thr: list) {
        	CmsTeacherVo vo = CommonUtil.createCopyProperties(thr, CmsTeacherVo.class);
        	ls.add(vo);
        }
        return ls;
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsteachers/{id}")
    public ResponseEntity<CmsTeacherVo> getTeacher(@PathVariable Long id) {
        log.debug("REST request to get a Teacher : {}", id);
        Optional<Teacher> teacher = this.teacherRepository.findById(id);
        CmsTeacherVo vo = null;
        if(teacher.isPresent()) {
        	Teacher th = teacher.get();
        	vo = CommonUtil.createCopyProperties(th,  CmsTeacherVo.class);
        }else {
        	vo = new  CmsTeacherVo();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsTeacherVo> getTeacherListByName(String name) {
    	Teacher teacher = null;
    	if(!CommonUtil.isNullOrEmpty(name)) {
    		teacher = new Teacher();
    		StringTokenizer token = new StringTokenizer(name, " ");
    		int cnt = 0;
    		while(token.hasMoreTokens()) {
    			if(cnt == 0) {
    				teacher.setTeacherName(token.nextToken());
    			}else if(cnt == 1) {
    				teacher.setTeacherMiddleName(token.nextToken());
    			}else if(cnt == 2) {
    				teacher.setTeacherLastName(token.nextToken());
    			}
    			cnt++;
    		}
    	}
        log.debug("REST request to get Teacher by name : {}", name);
        List<Teacher> list = null;
        if(teacher != null) {
        	list = teacherRepository.findAll(Example.of(teacher));
        }else {
        	list = Collections.emptyList();
        }
        
        List<CmsTeacherVo> ls = new ArrayList<>();
        for(Teacher st: list) {
        	CmsTeacherVo vo = CommonUtil.createCopyProperties(st, CmsTeacherVo.class);
        	ls.add(vo);
        }
        return ls;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsteachers/{id}")
    public Integer deleteTeacher(@PathVariable Long id) {
        log.debug("REST request to delete a Teacher : {}", id);
        try {
        	Teacher th = new Teacher();
            th.setId(id);
            th.setStatus(Status.DEACTIVE);
            this.teacherRepository.save(th);
        }catch(Exception e) {
    		return HttpStatus.FAILED_DEPENDENCY.value();
    	}
        return HttpStatus.OK.value();
    }

    

}
