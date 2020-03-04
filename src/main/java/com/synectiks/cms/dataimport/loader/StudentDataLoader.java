package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.BatchEnum;
import com.synectiks.cms.domain.enumeration.Bloodgroup;
import com.synectiks.cms.domain.enumeration.Caste;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.RelationWithStudentEnum;
import com.synectiks.cms.domain.enumeration.Religion;
import com.synectiks.cms.domain.enumeration.SectionEnum;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.exceptions.AdditionalStudentFoundException;
import com.synectiks.cms.exceptions.DataNotFoundException;
import com.synectiks.cms.exceptions.EmailIdExistsException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

public class StudentDataLoader extends DataLoader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private AllRepositories allRepositories;
    private String sheetName;

    public StudentDataLoader(String sheetName, AllRepositories allRepositories) {
        super(sheetName, allRepositories);
        this.allRepositories = allRepositories;
        this.sheetName = sheetName;
    }

    @Override
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalStudentFoundException,
    														DataNotFoundException, MandatoryFieldMissingException, EmailIdExistsException {

        StringBuilder sb = new StringBuilder();
        Student obj = CommonUtil.createCopyProperties(cls.newInstance(), Student.class);

        String status = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(status)) {
            sb.append("status, ");
            logger.warn("Mandatory field missing. Field name - status");
        } else {
        	if (Status.ACTIVE.toString().equalsIgnoreCase(status)) {
                obj.setStatus(Status.ACTIVE);
            } else if (Status.DEACTIVE.toString().equalsIgnoreCase(status)) {
                obj.setStatus(Status.DEACTIVE);
            }else if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
                obj.setStatus(Status.DRAFT);
            } else {
                sb.append("status,");
                logger.warn("status not listed in the system");
            }
        }


        String studentName = row.getCellAsString(1).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentName)) {
            sb.append("student_name, ");
            logger.warn("Mandatory field missing. Field name - student_name");
        } else {
            obj.setStudentName(studentName);
        }

        String studentMiddleName = row.getCellAsString(2).orElse(null);
        obj.setStudentMiddleName(studentMiddleName);

        String studentLastName = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentLastName)) {
            sb.append("student_last_name, ");
            logger.warn("Mandatory field missing. Field name - student_last_name");
        } else {
            obj.setStudentLastName(studentLastName);
        }

        String fatherName = row.getCellAsString(4).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setFatherName(fatherName);
        }else {
        	if (CommonUtil.isNullOrEmpty(fatherName)) {
                sb.append("father_name, ");
                logger.warn("Mandatory field missing. Field name - father_name");
            } else {
                obj.setFatherName(fatherName);
            }
        }

        String fatherMiddleName = row.getCellAsString(5).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setFatherMiddleName(fatherMiddleName);
        }else {
        	if (CommonUtil.isNullOrEmpty(fatherMiddleName)) {
                sb.append("father_middle_name, ");
                logger.warn("Mandatory field missing. Field name - father_middle_name");
            } else {
                obj.setFatherMiddleName(fatherMiddleName);
            }
        }

        String fatherLastName = row.getCellAsString(6).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setFatherLastName(fatherLastName);
        }else {
        	if (CommonUtil.isNullOrEmpty(fatherLastName)) {
                sb.append("father_last_name, ");
                logger.warn("Mandatory field missing. Field name - father_last_name");
            } else {
                obj.setFatherLastName(fatherLastName);
            }
        }

        String motherName = row.getCellAsString(7).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setMotherName(motherName);
        }else {
        	if (CommonUtil.isNullOrEmpty(motherName)) {
                sb.append("mother_name, ");
                logger.warn("Mandatory field missing. Field name - mother_name");
            } else {
                obj.setMotherName(motherName);
            }
        }


        String motherMiddleName = row.getCellAsString(8).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setMotherMiddleName(motherMiddleName);
        }else {
        	if (CommonUtil.isNullOrEmpty(motherMiddleName)) {
                sb.append("mother_middle_name, ");
                logger.warn("Mandatory field missing. Field name - mother_middle_name");
            } else {
                obj.setMotherMiddleName(motherMiddleName);
            }
        }

        String motherLastName = row.getCellAsString(9).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setMotherLastName(motherLastName);
        }else {
        	if (CommonUtil.isNullOrEmpty(motherLastName)) {
                sb.append("mother_last_name, ");
                logger.warn("Mandatory field missing. Field name - mother_last_name");
            } else {
                obj.setMotherLastName(motherLastName);
            }
        }

        String stAadharNo = row.getCellAsString(10).orElse(null);
        obj.setStudentAadharNo(stAadharNo);

        String stPanNo = row.getCellAsString(11).orElse(null);
        obj.setStudentPanNo(stPanNo);

        String studentSocialSecurityNo = row.getCellAsString(12).orElse(null);
        obj.setStudentSocialSecurityNo(studentSocialSecurityNo);

        String studentTaxReferenceNo = row.getCellAsString(13).orElse(null);
        obj.setStudentTaxReferenceNo(studentTaxReferenceNo);

        String studentBplNo = row.getCellAsString(14).orElse(null);
        obj.setStudentBplNo(studentBplNo);

        String studentDrivingLicenseNo = row.getCellAsString(15).orElse(null);
        obj.setStudentDrivingLicenseNo(studentDrivingLicenseNo);

        String studentPassportNo = row.getCellAsString(16).orElse(null);
        obj.setStudentPassportNo(studentPassportNo);

        String dateOfBirth = row.getCellAsString(17).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(dateOfBirth)) {
                obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dateOfBirth, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
        }else {
        	if (CommonUtil.isNullOrEmpty(dateOfBirth)) {
                sb.append("date_of_birth, ");
                logger.warn("Mandatory field missing. Field name - date_of_birth");
            } else {
                obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dateOfBirth, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
        }

        String placeOfBirth = row.getCellAsString(18).orElse(null);
        obj.setPlaceOfBirth(placeOfBirth);

        String religion = row.getCellAsString(19).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(religion)) {
        		if (Religion.HINDU.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.HINDU);
                } else if (Religion.MUSLIM.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.MUSLIM);
                } else if (Religion.CHRISTIAN.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.CHRISTIAN);
                } else if (Religion.BUDH.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.BUDH);
                } else if (Religion.SIKH.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.SIKH);
                }
        	}
        }else {
        	if (CommonUtil.isNullOrEmpty(religion)) {
                sb.append("religion, ");
                logger.warn("Mandatory field missing. Field name - religion");
            } else {
                if (Religion.HINDU.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.HINDU);
                } else if (Religion.MUSLIM.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.MUSLIM);
                } else if (Religion.CHRISTIAN.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.CHRISTIAN);
                } else if (Religion.BUDH.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.BUDH);
                } else if (Religion.SIKH.toString().equalsIgnoreCase(religion)) {
                    obj.setReligion(Religion.SIKH);
                }else {
                    sb.append("religion,");
                    logger.warn("Religion not listed in the system, Field name - religion");
                }
            }
        }

        String caste = row.getCellAsString(20).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(caste)) {
        		if (Caste.OBC.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.OBC);
                } else if (Caste.GENERAL.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.GENERAL);
                } else if (Caste.SC.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.SC);
                } else if (Caste.ST.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.ST);
                }
        	}
        }else {
        	if (CommonUtil.isNullOrEmpty(caste)) {
                sb.append("caste, ");
                logger.warn("Mandatory field missing. Field name - caste");
            } else {
                if (Caste.OBC.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.OBC);
                } else if (Caste.GENERAL.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.GENERAL);
                } else if (Caste.SC.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.SC);
                } else if (Caste.ST.toString().equalsIgnoreCase(caste)) {
                    obj.setCaste(Caste.ST);
                } else {
                    sb.append("cast, ");
                    logger.warn("Cast not listed in the system. Field name - caste");
                }
            }
        }

        String subCaste = row.getCellAsString(21).orElse(null);
        obj.setSubCaste(subCaste);

        String sex = row.getCellAsString(22).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(sex)) {
                if (Gender.MALE.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.MALE);
                }else if (Gender.FEMALE.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.FEMALE);
                }else if (Gender.OTHER.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.OTHER);
                }
            }
        }else {
        	if (CommonUtil.isNullOrEmpty(sex)) {
                sb.append("sex, ");
                logger.warn("Mandatory field missing. Field name - sex");
            } else {
                if (Gender.MALE.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.MALE);
                }else if (Gender.FEMALE.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.FEMALE);
                }else if (Gender.OTHER.toString().equalsIgnoreCase(sex)) {
                    obj.setSex(Gender.OTHER);
                } else{
                    sb.append("sex, ");
                    logger.warn("Sex not listed in the system. Field name - sex");
                }
            }
        }

	    String studentLocalAddress = row.getCellAsString(23).orElse(null);
	    if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
	    	obj.setStudentLocalAddress(studentLocalAddress);
	    }else {
	    	if (CommonUtil.isNullOrEmpty(studentLocalAddress)) {
		         sb.append("student_local_address, ");
		         logger.warn("Mandatory field missing. Field name - student_local_address");
		    } else {
		         obj.setStudentLocalAddress(studentLocalAddress);
		    }
	    }

	    String studentPermanentAddress = row.getCellAsString(24).orElse(null);
	    obj.setStudentPermanentAddress(studentPermanentAddress);

	    String countryName = row.getCellAsString(25).orElse(null);
	    Optional<Country> octry = null;
	    if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
	    	if(!CommonUtil.isNullOrEmpty(countryName)) {
	            Country country = new Country();
	            country.countryName(countryName);
	            octry = this.allRepositories.findRepository("country").findOne(Example.of(country));
	            if(octry != null && octry.isPresent()) {
	                obj.setCountry(octry.get().getCountryName());
	            }
	        }
	    }else {
	    	if(CommonUtil.isNullOrEmpty(countryName)) {
	            sb.append("country_id, ");
	            logger.warn("Mandatory field missing. Field name - country_id");
	        }else {
	            Country country = new Country();
	            country.countryName(countryName);
	            octry = this.allRepositories.findRepository("country").findOne(Example.of(country));
	            if(octry != null && octry.isPresent()) {
	                obj.setCountry(octry.get().getCountryName());
	            }else {
	                sb.append("country_id, ");
	                logger.warn("Country not found. Given country name : "+countryName);
	            }
	        }
	    }

        Optional<State> ostate = null;
        String stateName = row.getCellAsString(26).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if(!CommonUtil.isNullOrEmpty(stateName) && octry!= null && octry.isPresent()) {
                State state = new State();
                state.setStateName(stateName);
                state.setCountry(octry.get());
                ostate = this.allRepositories.findRepository("state").findOne(Example.of(state));
                if(ostate != null && ostate.isPresent()) {
                    obj.setState(ostate.get().getStateName());
                }
            }
        }else {
        	if(CommonUtil.isNullOrEmpty(stateName) || octry == null || (octry != null && !octry.isPresent())) {
                sb.append("state_id, ");
                logger.warn("Mandatory field missing. Field name - state_id");
            }else {
            	State state = new State();
                state.setStateName(stateName);
                state.setCountry(octry.get());
                ostate = this.allRepositories.findRepository("state").findOne(Example.of(state));
                if(ostate != null && ostate.isPresent()) {
                    obj.setState(ostate.get().getStateName());
                }else {
                    sb.append("state_id, ");
                    logger.warn("State not found. Given state name : "+stateName);
                }
            }
        }

        String cityName = row.getCellAsString(27).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if(!CommonUtil.isNullOrEmpty(cityName)) {
                Optional<City> c = null;
                if(ostate != null && ostate.isPresent()) {
                    City city = new City();
                    city.setCityName(cityName);
                    city.setState(ostate.get());
                    c = this.allRepositories.findRepository("city").findOne(Example.of(city));
                }
                if(c != null && c.isPresent()) {
                    obj.setCity(c.get().getCityName());
                }
            }
        }else {
        	if(CommonUtil.isNullOrEmpty(cityName) || ostate == null || (ostate != null && !ostate.isPresent())) {
                sb.append("town, ");
                logger.warn("Mandatory field missing. Field name - town");
            }else {
                Optional<City> c = null;
                if(ostate != null && ostate.isPresent()) {
                    City city = new City();
                    city.setCityName(cityName);
                    city.setState(ostate.get());
                    c = this.allRepositories.findRepository("city").findOne(Example.of(city));
                }
                if(c != null && c.isPresent()) {
                    obj.setCity(c.get().getCityName());
                }else {
                    sb.append("town, ");
                    logger.warn("Town/city not found. Given city/town name : "+cityName);
                }
            }
        }

        String pincode = row.getCellAsString(28).orElse(null);
        obj.setPinCode(pincode);

        String studentPrimaryCellNumber = row.getCellAsString(29).orElse(null);

        String studentAlternateCellNumber = row.getCellAsString(30).orElse(null);
        obj.setStudentAlternateCellNumber(studentAlternateCellNumber);

        String studentLandLinePhoneNumber = row.getCellAsString(31).orElse(null);
        obj.setStudentLandLinePhoneNumber(studentLandLinePhoneNumber);

        String studentPrimaryEmailId = row.getCellAsString(32).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setStudentPrimaryCellNumber(studentPrimaryCellNumber);
            obj.setStudentPrimaryEmailId(studentPrimaryEmailId);
        }else {
        	if(CommonUtil.isNullOrEmpty(studentPrimaryCellNumber ) && CommonUtil.isNullOrEmpty(studentPrimaryEmailId)) {
        		sb.append("student_primary_cell_number or student_primary_email_id, ");
                logger.warn("Either student_primary_cell_number or student_primary_email_id should be given ");
        	}
        }

        String studentAlternateEmailId = row.getCellAsString(33).orElse(null);
        obj.setStudentAlternateEmailId(studentAlternateEmailId);

        String relationWithStudent = row.getCellAsString(34).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(relationWithStudent)) {
                if (RelationWithStudentEnum.FATHER.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent(RelationWithStudentEnum.FATHER);
                } else if (RelationWithStudentEnum.MOTHER.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent((RelationWithStudentEnum.MOTHER));
                } else if(RelationWithStudentEnum.GUARDIAN.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent(RelationWithStudentEnum.GUARDIAN);
                }
            }
        }else {
        	if (CommonUtil.isNullOrEmpty(relationWithStudent)) {
                sb.append("relation_with_student, ");
                logger.warn("Mandatory field missing. Field name - relation_with_student");
            } else {
                if (RelationWithStudentEnum.FATHER.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent(RelationWithStudentEnum.FATHER);
                } else if (RelationWithStudentEnum.MOTHER.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent((RelationWithStudentEnum.MOTHER));
                } else if(RelationWithStudentEnum.GUARDIAN.toString().equalsIgnoreCase(relationWithStudent)){
                    obj.setRelationWithStudent(RelationWithStudentEnum.GUARDIAN);
                }else {
                    sb.append("relation_with_student,");
                    logger.warn("Given relation with student not listed. Field name - relation_with_student");
                }
            }
        }

        String emergencyContactName = row.getCellAsString(35).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setEmergencyContactName(emergencyContactName);
        }else {
        	if (CommonUtil.isNullOrEmpty(emergencyContactName)) {
                sb.append("emergency_contact_name, ");
                logger.warn("Mandatory field missing. Field name - emergency_contact_name");
            } else {
                obj.setEmergencyContactName(emergencyContactName);
            }
        }

        String emergencyContactMiddleName = row.getCellAsString(36).orElse(null);
        obj.setEmergencyContactMiddleName(emergencyContactMiddleName);

        String emergencyContactLastName = row.getCellAsString(37).orElse(null);
        obj.setEmergencyContactLastName(emergencyContactLastName);

        String emergencyContactCellNumber = row.getCellAsString(38).orElse(null);
        String emergencyContactLandLinePhoneNumber = row.getCellAsString(39).orElse(null);
        String emergencyContactEmailId = row.getCellAsString(40).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setEmergencyContactCellNumber(emergencyContactCellNumber);
        	obj.setEmergencyContactLandLinePhoneNumber(emergencyContactLandLinePhoneNumber);
        	obj.setEmergencyContactEmailId(emergencyContactEmailId);
        }else {
        	if (CommonUtil.isNullOrEmpty(emergencyContactCellNumber) && CommonUtil.isNullOrEmpty(emergencyContactLandLinePhoneNumber)
            		&& CommonUtil.isNullOrEmpty(emergencyContactEmailId)) {
            	sb.append("emergency_contact_cell_number or emergency_contact_land_line_phone_number or emergency_contact_email_id, ");
            	logger.warn("Either emergency_contact_cell_number or emergency_contact_land_line_phone_number or emergency_contact_email_id should be present");
            }else {
            	obj.setEmergencyContactCellNumber(emergencyContactCellNumber);
            	obj.setEmergencyContactLandLinePhoneNumber(emergencyContactLandLinePhoneNumber);
            	obj.setEmergencyContactEmailId(emergencyContactEmailId);
            }
        }

        String admissionNo = row.getCellAsString(41).orElse(null);
        obj.setAdmissionNo(admissionNo);

        String enrollmentNo = row.getCellAsString(42).orElse(null);
        obj.setEnrollmentNo(enrollmentNo);

        String rollNo = row.getCellAsString(43).orElse(null);
        obj.setRollNo(rollNo);

        String studentType = row.getCellAsString(44).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	if (!CommonUtil.isNullOrEmpty(studentType)) {
                if (StudentTypeEnum.REGULAR.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.REGULAR);
                }else if(StudentTypeEnum.SCHOLARSHIP.toString().equalsIgnoreCase(studentType)) {
                    obj.setStudentType(StudentTypeEnum.SCHOLARSHIP);
                }else if(StudentTypeEnum.OTHER_BENEFITS.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.OTHER_BENEFITS);
                }else if(StudentTypeEnum.STAFF_CONCESSION.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.STAFF_CONCESSION);
                }else if(StudentTypeEnum.BENEFITS.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.BENEFITS);
                }
            }
        }else {
        	if (CommonUtil.isNullOrEmpty(studentType)) {
                sb.append("student_type, ");
                logger.warn("Mandatory field missing. Field name - student_type");
            } else {
                if (StudentTypeEnum.REGULAR.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.REGULAR);
                }else if(StudentTypeEnum.SCHOLARSHIP.toString().equalsIgnoreCase(studentType)) {
                    obj.setStudentType(StudentTypeEnum.SCHOLARSHIP);
                }else if(StudentTypeEnum.OTHER_BENEFITS.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.OTHER_BENEFITS);
                }else if(StudentTypeEnum.STAFF_CONCESSION.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.STAFF_CONCESSION);
                }else if(StudentTypeEnum.BENEFITS.toString().equalsIgnoreCase(studentType)){
                    obj.setStudentType(StudentTypeEnum.BENEFITS);
                }else {
                    sb.append("student_type,");
                    logger.warn("Given student type not listed. Field name - student_type");
                }
            }
        }

        String fatherCellNumber = row.getCellAsString(45).orElse(null);
        obj.setFatherCellNumber(fatherCellNumber);

        String fatherEmailId = row.getCellAsString(46).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setFatherEmailId(fatherEmailId);
        }else {
        	if (CommonUtil.isNullOrEmpty(fatherEmailId)) {
                sb.append("father_email_id, ");
                logger.warn("Mandatory field missing. Field name - father_email_id");
            } else {
            	obj.setFatherEmailId(fatherEmailId);
            }
        }

        String fatherOccupation = row.getCellAsString(47).orElse(null);
        obj.setFatherOccupation(fatherOccupation);

        String fatherOfficeEmailId = row.getCellAsString(48).orElse(null);
        obj.setFatherOfficeEmailId(fatherOfficeEmailId);

        String fatherOfficeAddress = row.getCellAsString(49).orElse(null);
        obj.setFatherOfficeAddress(fatherOfficeAddress);

        String fatherOfficeCellNumber = row.getCellAsString(50).orElse(null);
        obj.setFatherOfficeCellNumber(fatherOfficeCellNumber);

        String fatherOfficeLandLinePhoneNumber = row.getCellAsString(51).orElse(null);
        obj.setFatherOfficeLandLinePhoneNumber(fatherOfficeLandLinePhoneNumber);

        String fatherAadharNo = row.getCellAsString(52).orElse(null);
        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	obj.setFatherAadharNo(fatherAadharNo);
        }else {
        	if (CommonUtil.isNullOrEmpty(fatherAadharNo)) {
                sb.append("father_aadhar_no, ");
                logger.warn("Mandatory field missing. Field name - father_aadhar_no");
            } else {
            	obj.setFatherAadharNo(fatherAadharNo);
            }
        }

        String fatherPanNo = row.getCellAsString(53).orElse(null);
        obj.setFatherPanNo(fatherPanNo);

        String fatherSocialSecurityNo = row.getCellAsString(54).orElse(null);
        obj.setFatherSocialSecurityNo(fatherSocialSecurityNo);

        String fatherTaxReferenceNo = row.getCellAsString(55).orElse(null);
        obj.setFatherTaxReferenceNo(fatherTaxReferenceNo);

        String fatherBplNo = row.getCellAsString(56).orElse(null);
        obj.setFatherBplNo(fatherBplNo);

        String fatherDrivingLicenseNo = row.getCellAsString(57).orElse(null);
        obj.setFatherDrivingLicenseNo(fatherDrivingLicenseNo);

        String fatherPassportNo = row.getCellAsString(58).orElse(null);
        obj.setFatherPassportNo(fatherPassportNo);

        String motherCellNumber = row.getCellAsString(59).orElse(null);
        obj.setMotherCellNumber(motherCellNumber);

        String motherEmailId = row.getCellAsString(60).orElse(null);
        obj.setMotherEmailId(motherEmailId);

        String motherOccupation = row.getCellAsString(61).orElse(null);
        obj.setMotherOccupation(motherOccupation);

        String motherOfficeEmailId = row.getCellAsString(62).orElse(null);
        obj.setMotherOfficeEmailId(motherOfficeEmailId);

        String motherOfficeAddress = row.getCellAsString(63).orElse(null);
        obj.setMotherOfficeAddress(motherOfficeAddress);

        String motherOfficeCellNumber = row.getCellAsString(64).orElse(null);
        obj.setMotherOfficeCellNumber(motherOfficeCellNumber);

        String motherOfficeLandLinePhoneNumber = row.getCellAsString(65).orElse(null);
        obj.setMotherOfficeLandLinePhoneNumber(motherOfficeLandLinePhoneNumber);

        String motherAadharNo = row.getCellAsString(66).orElse(null);
        obj.setMotherAadharNo(motherAadharNo);

        String motherPanNo = row.getCellAsString(67).orElse(null);
        obj.setMotherPanNo(motherPanNo);

        String motherSocialSecurityNo = row.getCellAsString(68).orElse(null);
        obj.setMotherSocialSecurityNo(motherSocialSecurityNo);

        String motherTaxReferenceNo = row.getCellAsString(69).orElse(null);
        obj.setMotherTaxReferenceNo(motherTaxReferenceNo);

        String motherBplNo = row.getCellAsString(70).orElse(null);
        obj.setMotherBplNo(motherBplNo);

        String motherDrivingLicenseNo = row.getCellAsString(71).orElse(null);
        obj.setMotherDrivingLicenseNo(motherDrivingLicenseNo);

        String motherPassportNo = row.getCellAsString(72).orElse(null);
        obj.setMotherPassportNo(motherPassportNo);

        String guardianName = row.getCellAsString(73).orElse(null);
        obj.setGuardianName(guardianName);

        String guardianMiddleName = row.getCellAsString(74).orElse(null);
        obj.setGuardianMiddleName(guardianMiddleName);

        String guardianLastName = row.getCellAsString(75).orElse(null);
        obj.setGuardianLastName(guardianLastName);

        String guardianAddress = row.getCellAsString(76).orElse(null);
        obj.setGuardianAddress(guardianAddress);

        String guardianCellNumber = row.getCellAsString(77).orElse(null);
        obj.setGuardianCellNumber(guardianCellNumber);

        String guardianLandLinePhoneNumber = row.getCellAsString(78).orElse(null);
        obj.setGuardianLandLinePhoneNumber(guardianLandLinePhoneNumber);

        String guardianEmailId = row.getCellAsString(79).orElse(null);
        obj.setGuardianEmailId(guardianEmailId);

        String guardianOccupation = row.getCellAsString(80).orElse(null);
        obj.setGuardianOccupation(guardianOccupation);

        String guardianOfficeEmailId = row.getCellAsString(81).orElse(null);
        obj.setGuardianOfficeEmailId(guardianOfficeEmailId);

        String guardianOfficeAddress = row.getCellAsString(82).orElse(null);
        obj.setGuardianOfficeAddress(guardianOfficeAddress);

        String guardianOfficeCellNumber = row.getCellAsString(83).orElse(null);
        obj.setGuardianOfficeCellNumber(guardianOfficeCellNumber);

        String guardianOfficeLandLinePhoneNumber = row.getCellAsString(84).orElse(null);
        obj.setGuardianOfficeLandLinePhoneNumber(guardianOfficeLandLinePhoneNumber);

        String isGuardianSponsorAgency = row.getCellAsString(85).orElse(null);
        obj.setIsGuardianSponsorAgency(isGuardianSponsorAgency);

        if (Status.DRAFT.toString().equalsIgnoreCase(status)) {
        	String sponsorAgencyName = row.getCellAsString(86).orElse(null);
            obj.setSponsorAgencyName(sponsorAgencyName);

            String sponsorAgencyRegistrationNo = row.getCellAsString(87).orElse(null);
            obj.setSponsorAgencyRegistrationNo(sponsorAgencyRegistrationNo);

            String sponsorAgencyAddress = row.getCellAsString(88).orElse(null);
            obj.setSponsorAgencyAddress(sponsorAgencyAddress);

            String sponsorAgencyCellNumber = row.getCellAsString(89).orElse(null);
            obj.setSponsorAgencyCellNumber(sponsorAgencyCellNumber);

            String sponsorAgencyLandLineNumber = row.getCellAsString(90).orElse(null);
            obj.setSponsorAgencyLandLineNumber(sponsorAgencyLandLineNumber);

            String sponsorAgencyEmailId = row.getCellAsString(91).orElse(null);
            obj.setSponsorAgencyEmailId(sponsorAgencyEmailId);


        }else {
        	if("YES".equalsIgnoreCase(isGuardianSponsorAgency)) {
        		String sponsorAgencyName = row.getCellAsString(86).orElse(null);
        		if (CommonUtil.isNullOrEmpty(sponsorAgencyName)) {
                    sb.append("sponsor_agency_name, ");
                    logger.warn("Mandatory field missing. Field name - sponsor_agency_name");
                } else {
                	obj.setSponsorAgencyName(sponsorAgencyName);
                }

        		String sponsorAgencyRegistrationNo = row.getCellAsString(87).orElse(null);
        		if (CommonUtil.isNullOrEmpty(sponsorAgencyRegistrationNo)) {
                    sb.append("sponsor_agency_registration_no, ");
                    logger.warn("Mandatory field missing. Field name - sponsor_agency_registration_no");
                } else {
                	obj.setSponsorAgencyRegistrationNo(sponsorAgencyRegistrationNo);
                }

                String sponsorAgencyAddress = row.getCellAsString(88).orElse(null);
                if (CommonUtil.isNullOrEmpty(sponsorAgencyAddress)) {
                    sb.append("sponsor_agency_address, ");
                    logger.warn("Mandatory field missing. Field name - sponsor_agency_address");
                } else {
                	obj.setSponsorAgencyAddress(sponsorAgencyAddress);
                }

                String sponsorAgencyCellNumber = row.getCellAsString(89).orElse(null);
                String sponsorAgencyLandLineNumber = row.getCellAsString(90).orElse(null);
                if (CommonUtil.isNullOrEmpty(sponsorAgencyCellNumber) && CommonUtil.isNullOrEmpty(sponsorAgencyLandLineNumber)) {
                    sb.append("sponsor_agency_cell_number or sponsor_agency_land_line_number, ");
                    logger.warn("Mandatory field missing. Either sponsor_agency_cell_number or sponsor_agency_land_line_number should be given");
                } else {
                	obj.setSponsorAgencyCellNumber(sponsorAgencyCellNumber);
                	obj.setSponsorAgencyLandLineNumber(sponsorAgencyLandLineNumber);
                }

                String sponsorAgencyEmailId = row.getCellAsString(91).orElse(null);
                if (CommonUtil.isNullOrEmpty(sponsorAgencyEmailId)) {
                    sb.append("sponsorAgencyEmailId, ");
                    logger.warn("Mandatory field missing. Field name - sponsorAgencyEmailId");
                } else {
                	obj.setSponsorAgencyEmailId(sponsorAgencyEmailId);
                }

        	}
        }

        String sponsorAgencyAppointeeName = row.getCellAsString(92).orElse(null);
        obj.setSponsorAgencyAppointeeName(sponsorAgencyAppointeeName);

        String sponsorAgencyAppointeeDesignation = row.getCellAsString(93).orElse(null);
        obj.setSponsorAgencyAppointeeDesignation(sponsorAgencyAppointeeDesignation);

        String sponsorAgencyAppointeeCellNumber = row.getCellAsString(94).orElse(null);
        obj.setSponsorAgencyAppointeeCellNumber(sponsorAgencyAppointeeCellNumber);

        String sponsorAgencyAppointeeLandLineNumber = row.getCellAsString(95).orElse(null);
        obj.setSponsorAgencyAppointeeLandLineNumber(sponsorAgencyAppointeeLandLineNumber);

        String sponsorAgencyAppointeeEmailId = row.getCellAsString(96).orElse(null);
        obj.setSponsorAgencyAppointeeEmailId(sponsorAgencyAppointeeEmailId);

        String sponsorAgencyAppointeeOfficeAddress = row.getCellAsString(97).orElse(null);
        obj.setSponsorAgencyAppointeeOfficeAddress(sponsorAgencyAppointeeOfficeAddress);

        String isPhysicallyChallenged = row.getCellAsString(98).orElse(null);
        obj.setIsPhysicallyChallenged(isPhysicallyChallenged);

        if (Status.DRAFT.toString().equalsIgnoreCase(status) || CommonUtil.isNullOrEmpty(isPhysicallyChallenged)
        		|| "NO".equalsIgnoreCase(isPhysicallyChallenged)) {
        	String detailsOfDisability = row.getCellAsString(99).orElse(null);
            obj.setDetailsOfDisability(detailsOfDisability);

            String disabilityCertificateNo = row.getCellAsString(100).orElse(null);
            obj.setDisabilityCertificateNo(disabilityCertificateNo);

            String disabilityCertificateIssueAuthority = row.getCellAsString(101).orElse(null);
            obj.setDisabilityCertificateIssueAuthority(disabilityCertificateIssueAuthority);

            String disabilityCertificateIssueDate = row.getCellAsString(102).orElse(null);
            if (!CommonUtil.isNullOrEmpty(disabilityCertificateIssueDate)) {
                obj.setDisabilityCertificateIssueDate(DateFormatUtil.convertStringToLocalDate(disabilityCertificateIssueDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            }
        }else {
        	if("YES".equalsIgnoreCase(isPhysicallyChallenged)) {
        		String detailsOfDisability = row.getCellAsString(99).orElse(null);
        		if (CommonUtil.isNullOrEmpty(detailsOfDisability)) {
                    sb.append("details_of_disability, ");
                    logger.warn("Mandatory field missing. Field name - details_of_disability");
                } else {
                	obj.setDetailsOfDisability(detailsOfDisability);
                }

                String disabilityCertificateNo = row.getCellAsString(100).orElse(null);
                if (CommonUtil.isNullOrEmpty(disabilityCertificateNo)) {
                    sb.append("disability_certificate_no, ");
                    logger.warn("Mandatory field missing. Field name - disability_certificate_no");
                } else {
                	obj.setDisabilityCertificateNo(disabilityCertificateNo);
                }

                String disabilityCertificateIssueAuthority = row.getCellAsString(101).orElse(null);
                if (CommonUtil.isNullOrEmpty(disabilityCertificateIssueAuthority)) {
                    sb.append("disability_certificate_issue_authority, ");
                    logger.warn("Mandatory field missing. Field name - disability_certificate_issue_authority");
                } else {
                	obj.setDisabilityCertificateIssueAuthority(disabilityCertificateIssueAuthority);
                }

                String disabilityCertificateIssueDate = row.getCellAsString(102).orElse(null);
                if (CommonUtil.isNullOrEmpty(disabilityCertificateIssueDate)) {
                    sb.append("disability_certificate_issue_date, ");
                    logger.warn("Mandatory field missing. Field name - disability_certificate_issue_date");
                } else {
                	if (!CommonUtil.isNullOrEmpty(disabilityCertificateIssueDate)) {
                        obj.setDisabilityCertificateIssueDate(DateFormatUtil.convertStringToLocalDate(disabilityCertificateIssueDate, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                    }
                }
        	}
        }

        String percentagOfDisability = row.getCellAsString(103).orElse(null);
        if (!CommonUtil.isNullOrEmpty(percentagOfDisability)) {
        	obj.setPercentagOfDisability(Integer.parseInt(percentagOfDisability));
        }

        String bloodGroup = row.getCellAsString(104).orElse(null);
        if (CommonUtil.isNullOrEmpty(bloodGroup)) {
            sb.append("blood_group, ");
            logger.warn("Mandatory field missing. Field name - blood_group");
        } else {
            if (Bloodgroup.APOSITIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.APOSITIVE);
            }else if (Bloodgroup.ANEGATIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.ANEGATIVE);
            }else if (Bloodgroup.ABPOSITIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.ABPOSITIVE);
            }else if (Bloodgroup.ABNEGATIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.ABNEGATIVE);
            }else if (Bloodgroup.BPOSITIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.BPOSITIVE);
            }else if (Bloodgroup.BNEGATIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.BNEGATIVE);
            }else if (Bloodgroup.OPOSITIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.OPOSITIVE);
            }else if (Bloodgroup.ONEGATIVE.toString().equalsIgnoreCase(bloodGroup)) {
                obj.setBloodGroup(Bloodgroup.ONEGATIVE);
            }else {
                sb.append("blood_group,");
                logger.warn("Blood Group not listed in the system. Field name -blood_group");
            }
        }

        String vaccinationDetails = row.getCellAsString(105).orElse(null);
        obj.setVaccinationDetails(vaccinationDetails);

        String otherMedicalDetails = row.getCellAsString(106).orElse(null);
        obj.setOtherMedicalDetails(otherMedicalDetails);

        String comments = row.getCellAsString(107).orElse(null);
        obj.setComments(comments);

        String branchName = row.getCellAsString(108).orElse(null);
        String branchAddress = row.getCellAsString(109).orElse(null);
        Optional<Branch> b = null;
        if(CommonUtil.isNullOrEmpty(branchName) || CommonUtil.isNullOrEmpty(branchAddress)) {
        	sb.append("branch_id, ");
            logger.warn("Mandatory field missing. Branch name or branch address not provided, Cannot find the branch");
        }else {
            Branch branch = new Branch();
            branch.setBranchName(branchName);
            branch.address1(branchAddress);
            b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
            if(!b.isPresent()) {
            	sb.append("branch_id, ");
                logger.warn("Mandatory field missing. Branch not found");
            }else {
            	obj.setBranchId(b.get().getId());
            }
        }


        Optional<Department> dp = null;
        String departmentName = row.getCellAsString(110).orElse(null);
        if(CommonUtil.isNullOrEmpty(departmentName)) {
        	sb.append("department, ");
            logger.warn("Mandatory field missing. Field name - departmentName");
        }else {
        	Department department = new Department();
            department.setName(departmentName);
//            department.setBranch(obj.getBranch());
            dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
            if(!dp.isPresent()) {
            	sb.append("department, ");
                logger.warn("Mandatory field missing. Field name - departmentName");
            }else {
            	obj.setDepartmentId(dp.get().getId());
            }
        }

        Batch batch = new Batch();
        Optional<Batch> obatch = null;
        String batchName = row.getCellAsString(111).orElse(null);
        if(CommonUtil.isNullOrEmpty(batchName)){
            sb.append("section_id,");
            logger.warn("Either batch name not provided, Cannot identify that given section belongs to which batch");
        }else {
            if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.FIRSTYEAR);
            }else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.SECONDYEAR);
            }else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.THIRDYEAR);
            }else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.FOURTHYEAR);
            }else {
            	sb.append("batch_id, ");
                logger.warn("Given batch/year not listed. batch/year - "+batchName);
            }
//            batch.setDepartment(obj.getDepartment());
        	obatch = this.allRepositories.findRepository("batch").findOne(Example.of(batch));
        	if(!obatch.isPresent()) {
        		sb.append("batch, ");
                logger.warn("Given batch is missing. Field name - batch");
        	}else {
        		obj.setBatchId(obatch.get().getId());
        	}
        }

        String sectionId = row.getCellAsString(111).orElse(null);
        obj.setSectionId(Long.valueOf(sectionId));

        Optional<Section> osc = null;
		Section section = new Section();
        String sectionName = row.getCellAsString(112).orElse(null);
        if (CommonUtil.isNullOrEmpty(sectionName)) {
            sb.append("section_id,");
            logger.warn("Mandatory field missing. Field name - section_id");
        } else {
            if(SectionEnum.A.toString().equalsIgnoreCase(sectionName)){
            	section.setSection(SectionEnum.A);
            }else if (SectionEnum.A.toString().equalsIgnoreCase(sectionName)){
            	section.setSection(SectionEnum.B);
            }else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)){
            	section.setSection(SectionEnum.C);
            }else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)) {
            	section.setSection(SectionEnum.C);
            } else {
            	logger.warn("Given section not listed. Section - "+sectionName);
        		sb.append("section, ");
            }
            if(section.getSection() != null) {
//            	section.setBatch(obj.getBatch());
            	osc = this.allRepositories.findRepository("section").findOne(Example.of(section));
            	if(osc.isPresent()) {
            		obj.setSectionId(osc.get().getId());
            	}else {
            		sb.append("section, ");
                    logger.warn("Given section is missing. Field name - section. "+sectionName);
                }
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }

        if(!CommonUtil.isNullOrEmpty(studentPrimaryEmailId)){
        	Student st = new Student();
            st.setStudentPrimaryEmailId(studentPrimaryEmailId);
//            st.setBranch(obj.getBranch());
            long count = this.allRepositories.findRepository(this.sheetName).count(Example.of(st));
            if(count > 0) {
            	throw new EmailIdExistsException("Student primary email id alreay exists: Primary email id : "+studentPrimaryEmailId);
            }
        }

        return (T) obj;
    }
}
