package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.graphql.types.Student.StudentInput;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private CommonService commonService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName) {
        Student std = new Student();

        if(studentType != null) {
            std.setStudentType(studentType);
        }

        if(studentName != null){
            std.setStudentName(studentName);
        }

        if(gender != null) {
            std.setSex(gender);
        }
        if(departmentId != null) {
//            Department department = new Department();
//            department.setId(departmentId);
            std.setDepartmentId(departmentId);
        }

        if(batchId != null) {
//            Batch batch = new Batch();
//            batch.setId(batchId);
            std.setBatchId(batchId);
        }

        if(sectionId != null) {
//            Section section = new Section();
//            section.setId(sectionId);
            std.setSectionId(sectionId);
        }
        if(branchId != null) {
//            Branch branch = new Branch();
//            branch.setId(branchId);
            std.setBranchId(branchId);
        }
        Example<Student> example = Example.of(std);
        List<Student> list = this.studentRepository.findAll(example);
        return list;
    }


    public List<Student> searchStudent(StudentListFilterInput filter) {
        Student student = new Student();
        if(!CommonUtil.isNullOrEmpty(filter.getBranchId())) {
//        	Branch branch = this.commonService.getBranchById(Long.valueOf(filter.getBranchId()));
//        	if(branch != null) {
        		student.setBranchId(Long.parseLong(filter.getBranchId()));
//        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
//        	Department department = this.commonService.getDepartmentById(Long.valueOf(filter.getDepartmentId()));
//        	if(department != null) {
        		student.setDepartmentId(Long.parseLong(filter.getDepartmentId()));
//        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getBatchId())) {
//        	Batch batch = this.commonService.getBatchById(Long.valueOf(filter.getBatchId()));
//        	if(batch != null) {
        		student.setBatchId(Long.parseLong(filter.getBatchId()));
//        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getSectionId())) {
//        	Section section = this.commonService.getSectionById(Long.valueOf(filter.getSectionId()));
//        	if(section != null) {
        		student.setSectionId(Long.parseLong(filter.getSectionId()));
//        	}
        }
        if(!CommonUtil.isNullOrEmpty(filter.getGender())) {
        	if(filter.getGender().equalsIgnoreCase(Gender.MALE.toString())) {
        		student.setSex(Gender.MALE);
        	}else if(filter.getGender().equalsIgnoreCase(Gender.FEMALE.toString())) {
        		student.setSex(Gender.FEMALE);
        	}else if(filter.getGender().equalsIgnoreCase(Gender.OTHER.toString())) {
        		student.setSex(Gender.OTHER);
        	}
        }

        if(!CommonUtil.isNullOrEmpty(filter.getStudentType())) {
        	if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.REGULAR.toString())) {
        		student.setStudentType(StudentTypeEnum.REGULAR);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.STAFF_CONCESSION.toString())) {
        		student.setStudentType(StudentTypeEnum.STAFF_CONCESSION);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.BENEFITS.toString())) {
        		student.setStudentType(StudentTypeEnum.BENEFITS);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.SCHOLARSHIP.toString())) {
        		student.setStudentType(StudentTypeEnum.SCHOLARSHIP);
        	}else if(filter.getStudentType().equalsIgnoreCase(StudentTypeEnum.OTHER_BENEFITS.toString())) {
        		student.setStudentType(StudentTypeEnum.OTHER_BENEFITS);
        	}
        }

        Example<Student> example = Example.of(student);
        List<Student> list = this.studentRepository.findAll(example);
        return list;
    }

    public CmsStudentVo addStudent(StudentInput cmsStudentVo) {
        logger.info("Saving Student");
        CmsStudentVo vo = null;
        try {
            Student student = null;
            if (cmsStudentVo.getId() == null) {
                logger.debug("Adding new Student");
                student = CommonUtil.createCopyProperties(cmsStudentVo, Student.class);
                student.setCreatedOn(LocalDate.now());
            } else {
                logger.debug("Updating existing Student");
                student = this.studentRepository.findById(cmsStudentVo.getId()).get();
                student.setUpdatedOn(LocalDate.now());
            }

            student.setStudentName(cmsStudentVo.getStudentName());
            student.setStudentMiddleName(cmsStudentVo.getStudentMiddleName());
            student.setStudentLastName(cmsStudentVo.getStudentLastName());
            student.setFatherName(cmsStudentVo.getFatherName());
            student.setFatherMiddleName(cmsStudentVo.getFatherMiddleName());
            student.setFatherLastName(cmsStudentVo.getFatherLastName());
            student.setMotherName(cmsStudentVo.getMotherName());
            student.setMotherMiddleName(cmsStudentVo.getMotherMiddleName());
            student.setMotherLastName(cmsStudentVo.getMotherLastName());
            student.setStudentAadharNo(cmsStudentVo.getStudentAadharNo());
            student.setStudentPanNo(cmsStudentVo.getStudentPanNo());
            student.setStudentSocialSecurityNo(cmsStudentVo.getStudentSocialSecurityNo());
            student.setStudentTaxReferenceNo(cmsStudentVo.getStudentTaxReferenceNo());
            student.setStudentBplNo(cmsStudentVo.getStudentBplNo());
            student.setStudentDrivingLicenseNo(cmsStudentVo.getStudentDrivingLicenseNo());
            student.setStudentPassportNo(cmsStudentVo.getStudentPassportNo());
            student.setPlaceOfBirth(cmsStudentVo.getPlaceOfBirth());
            student.setReligion(cmsStudentVo.getReligion());
            student.setCaste(cmsStudentVo.getCaste());
            student.setCaste(cmsStudentVo.getCaste());
            student.setSubCaste(cmsStudentVo.getSubCaste());
            student.setAge(cmsStudentVo.getAge());
            student.setSex(cmsStudentVo.getSex());
            student.setStudentLocalAddress(cmsStudentVo.getStudentLocalAddress());
            student.setStudentPermanentAddress(cmsStudentVo.getStudentPermanentAddress());
            student.setCity(cmsStudentVo.getCity());
            student.setState(cmsStudentVo.getState());
            student.setCountry(cmsStudentVo.getCountry());
            student.setPinCode(cmsStudentVo.getPinCode());
            student.setStudentPrimaryCellNumber(cmsStudentVo.getStudentPrimaryCellNumber());
            student.setStudentAlternateCellNumber(cmsStudentVo.getStudentAlternateCellNumber());
            student.setStudentLandLinePhoneNumber(cmsStudentVo.getStudentLandLinePhoneNumber());
            student.setStudentPrimaryEmailId(cmsStudentVo.getStudentPrimaryEmailId());
            student.setStudentAlternateEmailId(cmsStudentVo.getStudentAlternateEmailId());
            student.setRelationWithStudent(cmsStudentVo.getRelationWithStudent());
            student.setEmergencyContactName(cmsStudentVo.getEmergencyContactName());
            student.setEmergencyContactMiddleName(cmsStudentVo.getEmergencyContactMiddleName());
            student.setEmergencyContactLastName(cmsStudentVo.getEmergencyContactLastName());
            student.setEmergencyContactCellNumber(cmsStudentVo.getEmergencyContactCellNumber());
            student.setEmergencyContactLandLinePhoneNumber(cmsStudentVo.getEmergencyContactLandLinePhoneNumber());
            student.setEmergencyContactEmailId(cmsStudentVo.getEmergencyContactEmailId());
            student.setStudentImagePath(cmsStudentVo.getStudentImagePath());
            student.setAdmissionNo(cmsStudentVo.getAdmissionNo());
            student.setEnrollmentNo(cmsStudentVo.getEnrollmentNo());
            student.setRollNo(cmsStudentVo.getRollNo());
            student.setStudentType(cmsStudentVo.getStudentType());
            student.setFatherCellNumber(cmsStudentVo.getFatherCellNumber());
            student.setFatherEmailId(cmsStudentVo.getFatherEmailId());
            student.setFatherOccupation(cmsStudentVo.getFatherOccupation());
            student.setFatherOfficeAddress(cmsStudentVo.getFatherOfficeAddress());
            student.setFatherOfficeCellNumber(cmsStudentVo.getFatherOfficeCellNumber());
            student.setFatherOfficeLandLinePhoneNumber(cmsStudentVo.getFatherOfficeLandLinePhoneNumber());
            student.setFatherAadharNo(cmsStudentVo.getFatherAadharNo());
            student.setFatherPanNo(cmsStudentVo.getFatherPanNo());
            student.setFatherSocialSecurityNo(cmsStudentVo.getFatherSocialSecurityNo());
            student.setFatherTaxReferenceNo(cmsStudentVo.getFatherTaxReferenceNo());
            student.setFatherBplNo(cmsStudentVo.getFatherBplNo());
            student.setFatherDrivingLicenseNo(cmsStudentVo.getFatherDrivingLicenseNo());
            student.setFatherPassportNo(cmsStudentVo.getFatherPassportNo());
            student.setFatherImagePath(cmsStudentVo.getFatherImagePath());
            student.setMotherCellNumber(cmsStudentVo.getMotherCellNumber());
            student.setMotherEmailId(cmsStudentVo.getMotherEmailId());
            student.setMotherOccupation(cmsStudentVo.getMotherOccupation());
            student.setMotherOfficeAddress(cmsStudentVo.getMotherOfficeAddress());
            student.setMotherOfficeCellNumber(cmsStudentVo.getMotherOfficeCellNumber());
            student.setMotherOfficeLandLinePhoneNumber(cmsStudentVo.getMotherOfficeLandLinePhoneNumber());
            student.setMotherAadharNo(cmsStudentVo.getMotherAadharNo());
            student.setMotherPanNo(cmsStudentVo.getMotherPanNo());
            student.setMotherSocialSecurityNo(cmsStudentVo.getMotherSocialSecurityNo());
            student.setMotherTaxReferenceNo(cmsStudentVo.getMotherTaxReferenceNo());
            student.setMotherBplNo(cmsStudentVo.getMotherBplNo());
            student.setMotherDrivingLicenseNo(cmsStudentVo.getMotherDrivingLicenseNo());
            student.setMotherPassportNo(cmsStudentVo.getMotherPassportNo());
            student.setMotherImagePath(cmsStudentVo.getMotherImagePath());
            student.setGuardianName(cmsStudentVo.getGuardianName());
            student.setGuardianMiddleName(cmsStudentVo.getGuardianMiddleName());
            student.setGuardianLastName(cmsStudentVo.getGuardianLastName());
            student.setGuardianAddress(cmsStudentVo.getGuardianAddress());
            student.setGuardianCellNumber(cmsStudentVo.getGuardianCellNumber());
            student.setGuardianLandLinePhoneNumber(cmsStudentVo.getGuardianLandLinePhoneNumber());
            student.setGuardianEmailId(cmsStudentVo.getGuardianEmailId());
            student.setGuardianOccupation(cmsStudentVo.getGuardianOccupation());
            student.setGuardianOfficeEmailId(cmsStudentVo.getGuardianOfficeEmailId());
            student.setGuardianOfficeAddress(cmsStudentVo.getGuardianOfficeAddress());
            student.setGuardianOfficeCellNumber(cmsStudentVo.getGuardianOfficeCellNumber());
            student.setGuardianOfficeLandLinePhoneNumber(cmsStudentVo.getGuardianOfficeLandLinePhoneNumber());
            student.setGuardianImagePath(cmsStudentVo.getGuardianImagePath());
            student.setIsGuardianSponsorAgency(cmsStudentVo.getIsGuardianSponsorAgency());
            student.setSponsorAgencyName(cmsStudentVo.getSponsorAgencyName());
            student.setSponsorAgencyRegistrationNo(cmsStudentVo.getSponsorAgencyRegistrationNo());
            student.setSponsorAgencyAddress(cmsStudentVo.getSponsorAgencyAddress());
            student.setSponsorAgencyCellNumber(cmsStudentVo.getSponsorAgencyCellNumber());
            student.setSponsorAgencyLandLineNumber(cmsStudentVo.getSponsorAgencyLandLineNumber());
            student.setSponsorAgencyEmailId(cmsStudentVo.getSponsorAgencyEmailId());
            student.setSponsorAgencyAppointeeName(cmsStudentVo.getSponsorAgencyAppointeeName());
            student.setSponsorAgencyAppointeeDesignation(cmsStudentVo.getSponsorAgencyAppointeeDesignation());
            student.setSponsorAgencyAppointeeCellNumber(cmsStudentVo.getSponsorAgencyAppointeeCellNumber());
            student.setSponsorAgencyAppointeeLandLineNumber(cmsStudentVo.getSponsorAgencyAppointeeLandLineNumber());
            student.setSponsorAgencyAppointeeOfficeAddress(cmsStudentVo.getSponsorAgencyAppointeeOfficeAddress());
            student.setSponsorAgencyAppointeeEmailId(cmsStudentVo.getSponsorAgencyAppointeeEmailId());
            student.setIsPhysicallyChallenged(cmsStudentVo.getIsPhysicallyChallenged());
            student.setDetailsOfDisability(cmsStudentVo.getDetailsOfDisability());
            student.setDisabilityCertificateNo(cmsStudentVo.getDisabilityCertificateNo());
            student.setDisabilityCertificateIssueAuthority(cmsStudentVo.getDisabilityCertificateIssueAuthority());
            student.setDisabilityCertificateNo(cmsStudentVo.getDisabilityCertificateNo());
            student.setPercentagOfDisability(cmsStudentVo.getPercentagOfDisability());
            student.setBloodGroup(cmsStudentVo.getBloodGroup());
            student.setVaccinationDetails(cmsStudentVo.getVaccinationDetails());
            student.setOtherMedicalDetails(cmsStudentVo.getOtherMedicalDetails());
            student.setStatus(cmsStudentVo.getStatus());
            student.setCreatedBy(cmsStudentVo.getCreatedBy());
            student.setUpdatedBy(cmsStudentVo.getUpdatedBy());
            student.setComments(cmsStudentVo.getComments());
            student.setBatchId(cmsStudentVo.getBatchId());
            student.setSectionId(cmsStudentVo.getSectionId());
            student.setDepartmentId(cmsStudentVo.getDepartmentId());
            student.setBranchId(cmsStudentVo.getBranchId());

            student.setDateOfBirth(cmsStudentVo.getStrDateOfBirth() != null ? DateFormatUtil.convertStringToLocalDate(cmsStudentVo.getStrDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            student = this.studentRepository.save(student);
            vo = CommonUtil.createCopyProperties(student, CmsStudentVo.class);
            vo.setStrDateOfBirth(student.getDateOfBirth() != null ? DateFormatUtil.changeLocalDateFormat(student.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrCreatedOn(student.getCreatedOn() != null ? DateFormatUtil.changeLocalDateFormat(student.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrUpdatedOn(student.getUpdatedOn() != null ? DateFormatUtil.changeLocalDateFormat(student.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (cmsStudentVo.getId() == null) {
                vo.setExitDescription("student is added successfully");
                logger.debug("student is added successfully");
            } else {
                vo.setExitDescription("student is updated successfully");
                logger.debug("student is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsStudentVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, student data not be saved");
            logger.error("Student save failed. Exception : ", e);
        }
        logger.info("Student saved successfully");
        return vo;
    }

}

