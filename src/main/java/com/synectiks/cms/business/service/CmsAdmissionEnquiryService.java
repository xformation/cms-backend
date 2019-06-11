package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsAdmissionEnquiryVo;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CmsAdmissionEnquiryService {
    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;

    public Long getTotalAdmissions(Long branchId, Long admissionApplicationId) {
        Long a = getTotalFollowup(branchId, admissionApplicationId);
        Long b = getTotalDeclined(branchId, admissionApplicationId);
        Long c = getTotalConverted(branchId, admissionApplicationId);
        return a + b + c;
    }

    public Long getTotalFollowup(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();

        if (branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            admissionEnquiry.setBranch(branch);
        }

        if (admissionApplicationId != null) {
            AdmissionApplication admissionApplication = new AdmissionApplication();
            admissionApplication.setId(admissionApplicationId);
            admissionEnquiry.setAdmissionApplication(admissionApplication);
        }

        admissionEnquiry.setStatus(EnquiryStatus.FOLLOWUP);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public Long getTotalDeclined(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();

        if (branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            admissionEnquiry.setBranch(branch);
        }

        if (admissionApplicationId != null) {
            AdmissionApplication admissionApplication = new AdmissionApplication();
            admissionApplication.setId(admissionApplicationId);
            admissionEnquiry.setAdmissionApplication(admissionApplication);
        }

        admissionEnquiry.setStatus(EnquiryStatus.DECLINED);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public Long getTotalConverted(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();

        if (branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            admissionEnquiry.setBranch(branch);
        }

        if (admissionApplicationId != null) {
            AdmissionApplication admissionApplication = new AdmissionApplication();
            admissionApplication.setId(admissionApplicationId);
            admissionEnquiry.setAdmissionApplication(admissionApplication);
        }

        admissionEnquiry.setStatus(EnquiryStatus.CONVERTED);
        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public List<CmsAdmissionEnquiryVo> searchAdmissionOnType(String admissionEnquiryType,Long branchId, Long admissionApplicationId) throws Exception {
        AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        Branch branch = new Branch();
        branch.setId(branchId);
        AdmissionApplication aa  = new AdmissionApplication();
        aa.setId(admissionApplicationId);

        admissionEnquiry.setBranch(branch);
        admissionEnquiry.setAdmissionApplication(aa);

        if(!admissionEnquiryType.equalsIgnoreCase("RECEIVED")) {
            if(admissionEnquiryType.equalsIgnoreCase("FOLLOWUP")) {
                admissionEnquiry.setStatus(EnquiryStatus.FOLLOWUP);
            }else if(admissionEnquiryType.equalsIgnoreCase("DECLINED")) {
                admissionEnquiry.setStatus(EnquiryStatus.DECLINED);
            }else if(admissionEnquiryType.equalsIgnoreCase("CONVERTED")) {
                admissionEnquiry.setStatus(EnquiryStatus.CONVERTED);
            }
        }

        Example<AdmissionEnquiry> example = Example.of(admissionEnquiry);
        List<AdmissionEnquiry> list = this.admissionEnquiryRepository.findAll(example);
        List<CmsAdmissionEnquiryVo> ls = new ArrayList<>();
        for(AdmissionEnquiry temp: list) {
            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, temp.getEnquiryDate());
            CmsAdmissionEnquiryVo cae = CommonUtil.createCopyProperties(temp, CmsAdmissionEnquiryVo.class);
            cae.setStrEnquiryDate(stDt);
            ls.add(cae);
        }
        return ls;
    }

}

