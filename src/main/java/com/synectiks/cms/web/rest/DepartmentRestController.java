package com.synectiks.cms.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsDepartmentVo;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.service.dto.DepartmentDTO;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class DepartmentRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ENTITY_NAME = "department";

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;
    
    @Autowired
    private CommonService commonService;
    

    @RequestMapping(method = RequestMethod.POST, value = "/cmsdepartments")
    public ResponseEntity<CmsDepartmentVo> createDepartment(@Valid @RequestBody CmsDepartmentVo cmsDepartmentVo) throws URISyntaxException{
        logger.info("REST request to create a new department.", cmsDepartmentVo);
        if (cmsDepartmentVo.getId()!= null){
            throw new BadRequestAlertException("A new department cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        Department d = new Department();
        Branch b = this.branchRepository.findById(cmsDepartmentVo.getBranchId()).get();
        AcademicYear a = this.academicYearRepository.findById(cmsDepartmentVo.getAcademicyearId()).get();
        d.setName(cmsDepartmentVo.getName());
        d.setDescription(cmsDepartmentVo.getDescription());
        d.setDeptHead(cmsDepartmentVo.getDeptHead());
        d.setBranch(b);
        d.setAcademicyear(a);
        Department result = departmentRepository.save(d);
        cmsDepartmentVo.setId(result.getId());
        return ResponseEntity.created(new URI("/api/cmsdepartments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(cmsDepartmentVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsdepartments")
    public ResponseEntity<CmsDepartmentVo> updateDepartment(@Valid @RequestBody CmsDepartmentVo cmsDepartmentVo) throws URISyntaxException {
        logger.info("REST request to update existing department.", cmsDepartmentVo);
        if (cmsDepartmentVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Department d = new Department();
        Branch b = this.branchRepository.findById(cmsDepartmentVo.getBranchId()).get();
        AcademicYear a = this.academicYearRepository.findById(cmsDepartmentVo.getAcademicyearId()).get();
        d.setName(cmsDepartmentVo.getName());
        d.setDescription(cmsDepartmentVo.getDescription());
        d.setDeptHead(cmsDepartmentVo.getDeptHead());
        d.setBranch(b);
        d.setAcademicyear(a);
        d.setId(cmsDepartmentVo.getId());
        Department result = departmentRepository.save(d);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsDepartmentVo.getId().toString()))
            .body(cmsDepartmentVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartments")
    public List<CmsDepartmentVo> getAllDepartments() {
        logger.debug("REST request to get all the departments.");
        List<Department> list = departmentRepository.findAll();
        List<CmsDepartmentVo> ls = new ArrayList<>();
        for(Department de : list) {
            CmsDepartmentVo vo = new CmsDepartmentVo();
            vo.setName(de.getName());
            vo.setDescription(de.getDescription());
            vo.setDeptHead(de.getDeptHead());
            vo.setBranch(de.getBranch());
            vo.setAcademicyear(de.getAcademicyear());
            vo.setId(de.getId());
            ls.add(vo);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartments-branchid/{id}")
    public List<CmsDepartmentVo> getAllDepartmentsByBranchId(@PathVariable Long id){
        if(!this.branchRepository.existsById(id)) {
            return Collections.emptyList();
        }
        List<CmsDepartmentVo> ls = this.commonService.getDepartmentListByBranch(id);
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsdepartments-academicyearid/{id}")
    public List<CmsDepartmentVo> getAllDepartmentsByAcademicYearId(@PathVariable Long id){
        if(!this.academicYearRepository.existsById(id)) {
            return Collections.emptyList();
        }
        AcademicYear academicYear = this.academicYearRepository.findById(id).get();
        Department department = new Department();
        department.setAcademicyear(academicYear);
        Example<Department> example = Example.of(department);
        List<Department> list = departmentRepository.findAll(example);
        List<CmsDepartmentVo> ls = new ArrayList<>();
        for(Department de : list) {
            CmsDepartmentVo vo = CommonUtil.createCopyProperties(de, CmsDepartmentVo.class);
            vo.setAcademicyearId(de.getAcademicyear().getId());
            ls.add(vo);
        }
        return ls;
    }

    @GetMapping("/cmsdepartments/{id}")
    public ResponseEntity<CmsDepartmentVo> getDepartment(@PathVariable Long id) {
    	logger.debug("REST request to get a Department : {}", id);
        Optional<Department> de = departmentRepository.findById(id);
        CmsDepartmentVo vo = CommonUtil.createCopyProperties(de.get(), CmsDepartmentVo.class);
        vo.setAcademicyearId(de.get().getAcademicyear().getId());
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsdepartments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        logger.debug("REST request to delete a Department : {}", id);
        departmentRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
