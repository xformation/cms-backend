package com.synectiks.cms.filter.admissionenquiry;

import com.synectiks.cms.business.service.CmsAdmissionEnquiryService;
import com.synectiks.cms.domain.CmsAdmissionEnquiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdmissionEnquiryProcessor {

    private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryProcessor.class);

    private Long totalAdmissions = 0L;
    private Long totalFollowup = 0L;
    private Long totalDeclined = 0L;
    private Long totalConverted = 0L;
    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;


    public Long getTotalAdmissions( Long branchId, Long admissionApplicationId ) {
        return cmsAdmissionEnquiryService.getTotalAdmissions(branchId, admissionApplicationId);
    }

    public Long getTotalFollowup(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalFollowup(branchId, admissionApplicationId);
    }

    public Long getTotalDeclined(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalDeclined(branchId, admissionApplicationId);
    }

    public Long getTotalConverted(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalConverted( branchId, admissionApplicationId);
    }

    public CmsAdmissionEnquiry getAdmissionData(Long branchId, Long admissionApplicationId) {
        CmsAdmissionEnquiry cmsAdmissionEnquiry = new CmsAdmissionEnquiry();
        cmsAdmissionEnquiry.setTotalAdmissions(this.getTotalAdmissions(branchId, admissionApplicationId));
        cmsAdmissionEnquiry.setTotalFollowup(this.getTotalFollowup(branchId, admissionApplicationId));
        cmsAdmissionEnquiry.setTotalDeclined(this.getTotalDeclined(branchId, admissionApplicationId));
        cmsAdmissionEnquiry.setTotalConverted(this.getTotalConverted(branchId, admissionApplicationId));
        return cmsAdmissionEnquiry;
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

