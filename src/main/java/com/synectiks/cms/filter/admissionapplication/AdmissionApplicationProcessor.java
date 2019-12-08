package com.synectiks.cms.filter.admissionapplication;

import com.synectiks.cms.business.service.CmsAdmissionApplicationService;
import com.synectiks.cms.entities.CmsAdmissionApplicationVo;
import com.synectiks.cms.entities.CmsAdmissionEnquiryVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AdmissionApplicationProcessor {

    private final Logger logger = LoggerFactory.getLogger(AdmissionApplicationProcessor.class);
    private Long totalReceived = 0L;
    private Long totalInprocess = 0L;
    private Long totalDeclined = 0L;
    private Long totalAccepted = 0L;
    @Autowired
    private CmsAdmissionApplicationService cmsAdmissionApplicationService;

    public Logger getLogger() {
        return logger;
    }


    public Long getTotalReceived( Long academicyearId ) {
        return cmsAdmissionApplicationService.getTotalReceived(academicyearId);
    }

    public Long getTotalInprocess(Long academicyearId) {
        return cmsAdmissionApplicationService.getTotalInprocess(academicyearId);
    }

    public Long getTotalDeclined(Long academicyearId) {
        return cmsAdmissionApplicationService.getTotalDeclined(academicyearId);
    }

    public Long getTotalAccepted(Long academicyearId) {
        return cmsAdmissionApplicationService.getTotalAccepted( academicyearId);
    }

    public CmsAdmissionApplicationVo getAdmissionApplicationData(Long academicyearId) {
        CmsAdmissionApplicationVo cmsAdmissionApplicationVo = new CmsAdmissionApplicationVo();
        cmsAdmissionApplicationVo.setTotalReceived(this.getTotalReceived(academicyearId));
        cmsAdmissionApplicationVo.setTotalInprocess(this.getTotalInprocess(academicyearId));
        cmsAdmissionApplicationVo.setTotalDeclined(this.getTotalDeclined(academicyearId));
        cmsAdmissionApplicationVo.setTotalAccepted(this.getTotalAccepted(academicyearId));
        return cmsAdmissionApplicationVo;
    }

    public List<CmsAdmissionApplicationVo> searchAdmissionApplicationOnType(String admissionApplicationType, Long academicyearId) throws Exception{
        return cmsAdmissionApplicationService.searchAdmissionOnType(admissionApplicationType, academicyearId);
    }

    public Long getTotalReceived() {
        return totalReceived;
    }

    public void setTotalReceived(Long totalReceived) {
        this.totalReceived = totalReceived;
    }

    public Long getTotalInprocess() {
        return totalInprocess;
    }

    public void setTotalInprocess(Long totalInprocess) {
        this.totalInprocess = totalInprocess;
    }

    public Long getTotalDeclined() {
        return totalDeclined;
    }

    public void setTotalDeclined(Long totalDeclined) {
        this.totalDeclined = totalDeclined;
    }

    public Long getTotalAccepted() {
        return totalAccepted;
    }

    public void setTotalAccepted(Long totalAccepted) {
        this.totalAccepted = totalAccepted;
    }

    public CmsAdmissionApplicationService getCmsAdmissionApplicationService() {
        return cmsAdmissionApplicationService;
    }

    public void setCmsAdmissionApplicationService(CmsAdmissionApplicationService cmsAdmissionApplicationService) {
        this.cmsAdmissionApplicationService = cmsAdmissionApplicationService;
    }

    @Override
    public String toString() {
        return "AdmissionApplicationProcessor{" +
            "totalReceived=" + totalReceived +
            ", totalInprocess=" + totalInprocess +
            ", totalDeclined=" + totalDeclined +
            ", totalAccepted=" + totalAccepted +
            ", cmsAdmissionApplicationService=" + cmsAdmissionApplicationService +
            '}';
    }
}
