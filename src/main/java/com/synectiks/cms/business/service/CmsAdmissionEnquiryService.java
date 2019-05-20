package com.synectiks.cms.business.service;


import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
import com.synectiks.cms.domain.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmsAdmissionEnquiryService {

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;


    public Long getTotalAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        Long a = getTotalFollowupAdmissionEnquiry(branchId, admissionApplicationId);
        Long b = getTotalConvertedAdmissionEnquiry(branchId, admissionApplicationId);
        Long c = getTotalDeclinedAdmissionEnquiry(branchId, admissionApplicationId);
        return a + b + c;
    }

    public Long getTotalFollowupAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry adm = new AdmissionEnquiry();

        if (branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            adm.setBranch(branch);
        }

        AdmissionApplication ad = new AdmissionApplication();
        ad.setId(admissionApplicationId);

        adm.setStatus(EnquiryStatus.FOLLOWUP);
        adm.setAdmissionApplication(ad);
        Example<AdmissionEnquiry> example = Example.of(adm);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public Long getTotalConvertedAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry adm = new AdmissionEnquiry();

        if (branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            adm.setBranch(branch);
        }

        AdmissionApplication ad = new AdmissionApplication();
        ad.setId(admissionApplicationId);

        adm.setStatus(EnquiryStatus.CONVERTED);
        adm.setAdmissionApplication(ad);
        Example<AdmissionEnquiry> example = Example.of(adm);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }

    public Long getTotalDeclinedAdmissionEnquiry(Long branchId, Long admissionApplicationId) {
        AdmissionEnquiry adm = new AdmissionEnquiry();

        if(branchId != null) {
            Branch branch = new Branch();
            branch.setId(branchId);
            adm.setBranch(branch);
        }

        AdmissionApplication ad = new AdmissionApplication();
        ad.setId(admissionApplicationId);

        adm.setStatus(EnquiryStatus.DECLINED);
        adm.setAdmissionApplication(ad);
        Example<AdmissionEnquiry> example = Example.of(adm);
        Long cnt = this.admissionEnquiryRepository.count(example);
        return cnt;
    }


    }
