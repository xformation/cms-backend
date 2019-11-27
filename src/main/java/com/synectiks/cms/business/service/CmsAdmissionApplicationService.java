package com.synectiks.cms.business.service;


import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.domain.enumeration.EnquiryStatus;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
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
public class CmsAdmissionApplicationService {
    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    public Long getTotalReceived(Long academicyearId) {
        Long a = getTotalInprocess(academicyearId);
        Long b = getTotalDeclined(academicyearId);
        Long c = getTotalAccepted(academicyearId);
        return a + b + c;
    }

    public Long getTotalDeclined(Long academicyearId) {
        AdmissionApplication admissionApplication = new AdmissionApplication();

        if (academicyearId != null) {
            AcademicYear academicyear = new AcademicYear();
            academicyear.setId(academicyearId);
            admissionApplication.setAcademicyear(academicyear);
        }



        admissionApplication.setAdmissionStatus(AdmissionStatusEnum.DECLINED);
        Example<AdmissionApplication> example = Example.of(admissionApplication);
        Long cnt = this.admissionApplicationRepository.count(example);
        return cnt;
    }

    public Long getTotalInprocess(Long academicyearId) {
        AdmissionApplication admissionApplication = new AdmissionApplication();

        if (academicyearId != null) {
            AcademicYear academicyear = new AcademicYear();
            academicyear.setId(academicyearId);
            admissionApplication.setAcademicyear(academicyear);
        }

        admissionApplication.setAdmissionStatus(AdmissionStatusEnum.INPROCESS);
        Example<AdmissionApplication> example = Example.of(admissionApplication);
        Long cnt = this.admissionApplicationRepository.count(example);
        return cnt;
    }

    public Long getTotalAccepted(Long academicyearId) {
        AdmissionApplication admissionApplication = new AdmissionApplication();

        if (academicyearId != null) {
            AcademicYear academicyear = new AcademicYear();
            academicyear.setId(academicyearId);
            admissionApplication.setAcademicyear(academicyear);
        }

        admissionApplication.setAdmissionStatus(AdmissionStatusEnum.ACCEPTED);
        Example<AdmissionApplication> example = Example.of(admissionApplication);
        Long cnt = this.admissionApplicationRepository.count(example);
        return cnt;
    }

    public List<CmsAdmissionApplicationVo> searchAdmissionOnType(String admissionApplicationType, Long academicyearId) throws Exception {
        AdmissionApplication admissionApplication = new AdmissionApplication();
        AcademicYear academicyear = new AcademicYear();
        academicyear.setId(academicyearId);
        admissionApplication.setAcademicyear(academicyear);

        if(!admissionApplicationType.equalsIgnoreCase("RECEIVED")) {
            if(admissionApplicationType.equalsIgnoreCase("INPROCESS")) {
                admissionApplication.setAdmissionStatus(AdmissionStatusEnum.INPROCESS);
            }else if(admissionApplicationType.equalsIgnoreCase("DECLINED")) {
                admissionApplication.setAdmissionStatus(AdmissionStatusEnum.DECLINED);
            }else if(admissionApplicationType.equalsIgnoreCase("ACCEPTED")) {
                admissionApplication.setAdmissionStatus(AdmissionStatusEnum.ACCEPTED);
            }
        }

        Example<AdmissionApplication> example = Example.of(admissionApplication);
        List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(example);
        List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
        for(AdmissionApplication temp: list) {
            CmsAdmissionApplicationVo cae = CommonUtil.createCopyProperties(temp, CmsAdmissionApplicationVo.class);
            cae.setStrAdmissionDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getAdmissionDate()))));

            ls.add(cae);
        }
        return ls;
    }


}
