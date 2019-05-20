package com.synectiks.cms.filter.admissionenquiry;

import com.synectiks.cms.business.service.CmsAdmissionEnquiryService;
import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.filter.invoice.InvoiceFilterProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdmissionEnquiryFilterProcessor {

    private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryFilterProcessor.class);

    private Long totalAdmissionEnquiry = 0L;
    private Long totalFollowupAdmissionEnquiry = 0L;
    private Long totalConvertedAdmissionEnquiry = 0L;
    private Long totalDeclinedAdmissionEnquiry = 0L;
    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    public Long getTotalAdmissionEnquiry (Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalAdmissionEnquiry(branchId, admissionApplicationId);
    }

    public Long getTotalFollowupAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalFollowupAdmissionEnquiry (branchId, admissionApplicationId);
    }

    public Long getTotalConvertedAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalConvertedAdmissionEnquiry(branchId, admissionApplicationId);
    }

    public Long getTotalDeclinedAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        return cmsAdmissionEnquiryService.getTotalDeclinedAdmissionEnquiry(branchId, admissionApplicationId);
    }

    public AdmissionEnquiryFilterProcessor getAdmissionEnquiryData(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiryFilterProcessor afp = new AdmissionEnquiryFilterProcessor();
        afp.setTotalAdmissionEnquiry(this.getTotalAdmissionEnquiry(branchId, admissionApplicationId));
        afp.setTotalFollowupAdmissionEnquiry(this.getTotalFollowupAdmissionEnquiry(branchId, admissionApplicationId));
        afp.setTotalConvertedAdmissionEnquiry(this.getTotalConvertedAdmissionEnquiry(branchId, admissionApplicationId));
        afp.setTotalDeclinedAdmissionEnquiry(this.getTotalDeclinedAdmissionEnquiry(branchId, admissionApplicationId));
        return afp;
    }

    public Logger getLogger() {
        return logger;
    }

    public Long getTotalAdmissionEnquiry() {
        return totalAdmissionEnquiry;
    }

    public void setTotalAdmissionEnquiry(Long totalAdmissionEnquiry) {
        this.totalAdmissionEnquiry = totalAdmissionEnquiry;
    }

    public Long getTotalFollowupAdmissionEnquiry() {
        return totalFollowupAdmissionEnquiry;
    }

    public void setTotalFollowupAdmissionEnquiry(Long totalFollowupAdmissionEnquiry) {
        this.totalFollowupAdmissionEnquiry = totalFollowupAdmissionEnquiry;
    }

    public Long getTotalConvertedAdmissionEnquiry() {
        return totalConvertedAdmissionEnquiry;
    }

    public void setTotalConvertedAdmissionEnquiry(Long totalConvertedAdmissionEnquiry) {
        this.totalConvertedAdmissionEnquiry = totalConvertedAdmissionEnquiry;
    }

    public Long getTotalDeclinedAdmissionEnquiry() {
        return totalDeclinedAdmissionEnquiry;
    }

    public void setTotalDeclinedAdmissionEnquiry(Long totalDeclinedAdmissionEnquiry) {
        this.totalDeclinedAdmissionEnquiry = totalDeclinedAdmissionEnquiry;
    }

    public CmsAdmissionEnquiryService getCmsAdmissionEnquiryService() {
        return cmsAdmissionEnquiryService;
    }

    public void setCmsAdmissionEnquiryService(CmsAdmissionEnquiryService cmsAdmissionEnquiryService) {
        this.cmsAdmissionEnquiryService = cmsAdmissionEnquiryService;
    }

    @Override
    public String toString() {
        return "AdmissionEnquiryFilterProcessor{" +
            "totalAdmissionEnquiry=" + totalAdmissionEnquiry +
            ", totalFollowupAdmissionEnquiry=" + totalFollowupAdmissionEnquiry +
            ", totalConvertedAdmissionEnquiry=" + totalConvertedAdmissionEnquiry +
            ", totalDeclinedAdmissionEnquiry=" + totalDeclinedAdmissionEnquiry +
            ", cmsAdmissionEnquiryService=" + cmsAdmissionEnquiryService +
            '}';
    }
}
