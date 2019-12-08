package com.synectiks.cms.filter.admissionenquiry;

import com.synectiks.cms.business.service.CmsAdmissionEnquiryService;
import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.entities.AdmissionEnquiry;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.CmsAdmissionEnquiryVo;
import com.synectiks.cms.entities.enumeration.EnquiryStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class AdmissionEnquiryProcessor {

    private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryProcessor.class);

    private Long totalAdmissions = 0L;
    private Long totalFollowup = 0L;
    private Long totalDeclined = 0L;
    private Long totalConverted = 0L;
    
    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @PersistenceContext
    private EntityManager entityManager;


    public Long getTotalAdmissions( Long branchId ) {
        return cmsAdmissionEnquiryService.getTotalAdmissions(branchId);
    }

    public Long getTotalFollowup(Long branchId) {
        return cmsAdmissionEnquiryService.getTotalFollowup(branchId);
    }

    public Long getTotalDeclined(Long branchId) {
        return cmsAdmissionEnquiryService.getTotalDeclined(branchId);
    }

    public Long getTotalConverted(Long branchId) {
        return cmsAdmissionEnquiryService.getTotalConverted( branchId);
    }

    public CmsAdmissionEnquiryVo getAdmissionData(Long branchId) {
        CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo = new CmsAdmissionEnquiryVo();
        cmsAdmissionEnquiryVo.setTotalAdmissions(this.getTotalAdmissions(branchId));
        cmsAdmissionEnquiryVo.setTotalFollowup(this.getTotalFollowup(branchId));
        cmsAdmissionEnquiryVo.setTotalDeclined(this.getTotalDeclined(branchId));
        cmsAdmissionEnquiryVo.setTotalConverted(this.getTotalConverted(branchId));
        return cmsAdmissionEnquiryVo;
    }

    public List<CmsAdmissionEnquiryVo> searchAdmissionOnType(String admissionEnquiryType,Long branchId) throws Exception{
        return cmsAdmissionEnquiryService.searchAdmissionOnType(admissionEnquiryType, branchId);
    }

    public List<CmsAdmissionEnquiryVo> admissionEnquiryList(Long branchId) throws Exception{
        return cmsAdmissionEnquiryService.admissionEnquiryList( branchId);
    }

    public Long getTotalAdmissions() {
        return totalAdmissions;
    }

    public void setTotalAdmissions(Long totalAdmissions) {
        this.totalAdmissions = totalAdmissions;
    }

    public Long getTotalFollowup() {
        return totalFollowup;
    }

    public void setTotalFollowup(Long totalFollowup) {
        this.totalFollowup = totalFollowup;
    }

    public Long getTotalDeclined() {
        return totalDeclined;
    }

    public void setTotalDeclined(Long totalDeclined) {
        this.totalDeclined = totalDeclined;
    }

    public Long getTotalConverted() {
        return totalConverted;
    }

    public void setTotalConverted(Long totalConverted) {
        this.totalConverted = totalConverted;
    }
    
    
    public Long getTotalEnquiry(Branch branch, AcademicYear academicYear, EnquiryStatus enqStatus) {
    	Object result = this.entityManager.createQuery("select count(ae.status) from AdmissionEnquiry ae where ae.enquiryDate between :startDate and :endDate and ae.status = :enqStatus and ae.branch = :br ")
    			.setParameter("startDate", academicYear.getStartDate())
    			.setParameter("endDate", academicYear.getEndDate())
    			.setParameter("enqStatus", enqStatus)
    			.setParameter("br", branch)
    			.getSingleResult();
    	Long totalConverted = (Long)result;
    	logger.debug("Total converted enquiries: "+totalConverted);
    	
    	return totalConverted ;
    }
    
    @Override
    public String toString() {
        return "AdmissionEnquiryProcessor{" +
            "totalAdmissions=" + totalAdmissions +
            ", totalFollowup=" + totalFollowup +
            ", totalDeclined=" + totalDeclined +
            ", totalConverted=" + totalConverted +
            ", cmsAdmissionEnquiryService=" + cmsAdmissionEnquiryService +
            '}';
    }
}

