package com.synectiks.cms.dataimport.loader;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.*;
import com.synectiks.cms.exceptions.AdditionalSectionFoundException;
import com.synectiks.cms.exceptions.AdditionalStudentFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.graphql.types.Student.StudentType;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.apache.poi.ss.formula.functions.T;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import java.util.Optional;

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
    public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalStudentFoundException, AdditionalSectionFoundException, MandatoryFieldMissingException {

        StringBuilder sb = new StringBuilder();
        Student obj = CommonUtil.createCopyProperties(cls.newInstance(), Student.class);


        String studentName = row.getCellAsString(0).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentName)) {
            sb.append("student_name, ");
            logger.warn("Mandatory field missing. Field name - student_name");
        } else {
            obj.setStudentName(studentName);
        }

        String studentMiddleName = row.getCellAsString(1).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentMiddleName)) {
            sb.append("student_middle_name, ");
            logger.warn("Mandatory field missing. Field name - student_middle_name");
        } else {
            obj.setStudentMiddleName(studentMiddleName);
        }

        String studentLastName = row.getCellAsString(2).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentLastName)) {
            sb.append("student_last_name, ");
            logger.warn("Mandatory field missing. Field name - student_last_name");
        } else {
            obj.setStudentLastName(studentLastName);
        }

        String fatherName = row.getCellAsString(3).orElse(null);
        if (CommonUtil.isNullOrEmpty(fatherName)) {
            sb.append("father_name, ");
            logger.warn("Mandatory field missing. Field name - father_name");
        } else {
            obj.setFatherName(fatherName);
        }

        String fatherMiddleName = row.getCellAsString(4).orElse(null);
        if (CommonUtil.isNullOrEmpty(fatherMiddleName)) {
            sb.append("father_middle_name, ");
            logger.warn("Mandatory field missing. Field name - father_middle_name");
        } else {
            obj.setFatherMiddleName(fatherMiddleName);
        }

        String fatherLastName = row.getCellAsString(5).orElse(null);
        if (CommonUtil.isNullOrEmpty(fatherLastName)) {
            sb.append("father_last_name, ");
            logger.warn("Mandatory field missing. Field name - father_last_name");
        } else {
            obj.setFatherLastName(fatherLastName);
        }

        String motherName = row.getCellAsString(6).orElse(null);
        if (CommonUtil.isNullOrEmpty(motherName)) {
            sb.append("mother_name, ");
            logger.warn("Mandatory field missing. Field name - mother_name");
        } else {
            obj.setMotherName(motherName);
        }

        String motherMiddleName = row.getCellAsString(7).orElse(null);
        if (CommonUtil.isNullOrEmpty(motherMiddleName)) {
            sb.append("mother_middle_name, ");
            logger.warn("Mandatory field missing. Field name - mother_middle_name");
        } else {
            obj.setMotherMiddleName(motherMiddleName);
        }

        String motherLastName = row.getCellAsString(8).orElse(null);
        if (CommonUtil.isNullOrEmpty(motherLastName)) {
            sb.append("mother_last_name, ");
            logger.warn("Mandatory field missing. Field name - mother_last_name");
        } else {
            obj.setMotherLastName(motherLastName);
        }

        String aadharNo = row.getCellAsString(9).orElse(null);
        if (CommonUtil.isNullOrEmpty(aadharNo)) {
            sb.append("aadhar_no, ");
            logger.warn("Mandatory field missing. Field name - aadhar_no");
        } else {
            obj.setAadharNo(Long.valueOf(aadharNo));
        }

        String dateOfBirth = row.getCellAsString(10).orElse(null);
        if (CommonUtil.isNullOrEmpty(dateOfBirth)) {
            sb.append("date_of_birth, ");
            logger.warn("Mandatory field missing. Field name - date_of_birth");
        } else {
            obj.setDateOfBirth(DateFormatUtil.convertStringToLocalDate(dateOfBirth, CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        }

        String placeOfBirth = row.getCellAsString(11).orElse(null);
        if (CommonUtil.isNullOrEmpty(placeOfBirth)) {
            sb.append("place_of_birth, ");
            logger.warn("Mandatory field missing. Field name - place_of_birth");
        } else {
            obj.setPlaceOfBirth(placeOfBirth);
        }

        String religion = row.getCellAsString(12).orElse(null);
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

        String caste = row.getCellAsString(13).orElse(null);
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


        String subCaste = row.getCellAsString(14).orElse(null);
        if (CommonUtil.isNullOrEmpty(subCaste)) {
            sb.append("sub_caste, ");
            logger.warn("Mandatory field missing. Field name - sub_caste");
        } else {
            obj.setSubCaste(subCaste);
        }

        String age = row.getCellAsString(15).orElse(null);
        if (CommonUtil.isNullOrEmpty(age)) {
            sb.append("age, ");
            logger.warn("Mandatory field missing. Field name - age");
        } else {
            obj.setAge(Integer.parseInt(age));
        }

        String sex = row.getCellAsString(16).orElse(null);
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


        String bloodGroup = row.getCellAsString(17).orElse(null);
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

        String addressLineOne = row.getCellAsString(18).orElse(null);
        if (CommonUtil.isNullOrEmpty(addressLineOne)) {
            sb.append("address_line_one, ");
            logger.warn("Mandatory field missing. Field name - address_line_one");
        } else {
            obj.setAddressLineOne(addressLineOne);
        }

        String addressLineTwo = row.getCellAsString(19).orElse(null);
        if (CommonUtil.isNullOrEmpty(addressLineTwo)) {
            sb.append("address_line_two, ");
            logger.warn("Mandatory field missing. Field name - address_line_two");
        } else {
            obj.setAddressLineTwo(addressLineTwo);
        }

        String addressLineThree = row.getCellAsString(20).orElse(null);
        if (CommonUtil.isNullOrEmpty(addressLineThree)) {
            sb.append("address_line_three, ");
            logger.warn("Mandatory field missing. Field name - address_line_three");
        } else {
            obj.setAddressLineThree(addressLineThree);
        }

        Optional<State> ostate = null;
        String stateName = row.getCellAsString(22).orElse(null);
        if(CommonUtil.isNullOrEmpty(stateName)) {
            sb.append("state_id, ");
            logger.warn("Mandatory field missing. Field name - state_id");
        }else {
            State state = new State();
            state.setStateName(stateName);
            ostate = this.allRepositories.findRepository("state").findOne(Example.of(state));
            if(ostate != null && ostate.isPresent()) {
                obj.setState(ostate.get().getStateName());
            }else {
                sb.append("state_id, ");
                logger.warn("State not found. Given state name : "+stateName);
            }
        }

        String cityName = row.getCellAsString(21).orElse(null);
        if(CommonUtil.isNullOrEmpty(cityName)) {
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
                obj.setTown(c.get().getCityName());
            }else {
                sb.append("town, ");
                logger.warn("Town/city not found. Given city/town name : "+cityName);
            }
        }

        String countryName = row.getCellAsString(23).orElse(null);
        if(CommonUtil.isNullOrEmpty(countryName)) {
            sb.append("country_id, ");
            logger.warn("Mandatory field missing. Field name - country_id");
        }else {
            Country country = new Country();
            country.countryName(countryName);
            Optional<Country> octry = this.allRepositories.findRepository("country").findOne(Example.of(country));
            if(octry.isPresent()) {
                obj.setCountry(octry.get().getCountryName());
            }else {
                sb.append("country_id, ");
                logger.warn("Country not found. Given country name : "+countryName);
            }
        }


        String pincode = row.getCellAsString(24).orElse(null);
        if (CommonUtil.isNullOrEmpty(pincode)) {
            sb.append("pincode, ");
            logger.warn("Mandatory field missing. Field name - pincode");
        } else {
            obj.setPincode(Long.parseLong(pincode));
        }

        String studentContactNumber = row.getCellAsString(25).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentContactNumber)) {
            sb.append("student_contact_number, ");
            logger.warn("Mandatory field missing. Field name - student_contact_number");
        } else {
            obj.setStudentContactNumber(studentContactNumber);
        }

        String alternateContactNumber = row.getCellAsString(26).orElse(null);
        if (CommonUtil.isNullOrEmpty(alternateContactNumber)) {
            sb.append("alternate_contact_number, ");
            logger.warn("Mandatory field missing. Field name - alternate_contact_number");
        } else {
            obj.setAlternateContactNumber(alternateContactNumber);
        }

        String studentEmailAddress = row.getCellAsString(27).orElse(null);
        if (CommonUtil.isNullOrEmpty(studentEmailAddress)) {
            sb.append("student_email_address, ");
            logger.warn("Mandatory field missing. Field name - student_email_address");
        } else {
            obj.setStudentEmailAddress(studentEmailAddress);
        }

        String alternateEmailAddress = row.getCellAsString(28).orElse(null);
        if (CommonUtil.isNullOrEmpty(alternateEmailAddress)) {
            sb.append("alternate_email_address, ");
            logger.warn("Mandatory field missing. Field name - alternate_email_address");
        } else {
            obj.setAlternateEmailAddress(alternateEmailAddress);
        }

        String relationWithStudent = row.getCellAsString(29).orElse(null);
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

        String emergencyContactName = row.getCellAsString(30).orElse(null);
        if (CommonUtil.isNullOrEmpty(emergencyContactName)) {
            sb.append("emergency_contact_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_name");
        } else {
            obj.setEmergencyContactName(emergencyContactName);
        }

        String emergencyContactMiddleName = row.getCellAsString(31).orElse(null);
        if (CommonUtil.isNullOrEmpty(emergencyContactMiddleName)) {
            sb.append("emergency_contact_middle_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_middle_name");
        } else {
            obj.setEmergencyContactMiddleName(emergencyContactMiddleName);
        }

        String emergencyContactLastName = row.getCellAsString(32).orElse(null);
        if (CommonUtil.isNullOrEmpty(emergencyContactLastName)) {
            sb.append("emergency_contact_last_name, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_last_name");
        } else {
            obj.setEmergencyContactLastName(emergencyContactLastName);
        }

        String emergencyContactNo = row.getCellAsString(33).orElse(null);
        if (CommonUtil.isNullOrEmpty(emergencyContactNo)) {
            sb.append("emergency_contact_no, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_no");
        } else {
            obj.setEmergencyContactNo(emergencyContactNo);
        }

        String emergencyContactEmailAddress = row.getCellAsString(34).orElse(null);
        if (CommonUtil.isNullOrEmpty(emergencyContactEmailAddress)) {
            sb.append("emergency_contact_email_address, ");
            logger.warn("Mandatory field missing. Field name - emergency_contact_email_address");
        } else {
            obj.setEmergencyContactEmailAddress(emergencyContactEmailAddress);
        }

        obj.setUploadPhoto(""); // cell 35

        String admissionNo = row.getCellAsString(36).orElse(null);
        if (CommonUtil.isNullOrEmpty(admissionNo)) {
            sb.append("admission_no, ");
            logger.warn("Mandatory field missing. Field name - admission_no");
        } else {
            obj.setAdmissionNo(Long.parseLong(admissionNo));
        }

        String rollNo = row.getCellAsString(37).orElse(null);
        if (CommonUtil.isNullOrEmpty(rollNo)) {
            sb.append("roll_no, ");
            logger.warn("Mandatory field missing. Field name - roll_no");
        } else {
            obj.setRollNo(rollNo);
        }

        String studentType = row.getCellAsString(38).orElse(null);
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

        String departmentName = row.getCellAsString(39).orElse(null);
        if(CommonUtil.isNullOrEmpty(departmentName)) {
            sb.append("department_id, ");
            logger.warn("Mandatory field missing. Field name - department_id");
        }else {
            String branchName = row.getCellAsString(42).orElse(null);
            String branchAddress = row.getCellAsString(43).orElse(null);
            if(CommonUtil.isNullOrEmpty(branchName) || CommonUtil.isNullOrEmpty(branchAddress)) {
                sb.append("department_id, ");
                logger.warn("Either branch name or branch address not provided, Cannot identify that given department belongs to which branch");
            }else {
                Branch branch = new Branch();
                branch.setBranchName(branchName);
                branch.address1(branchAddress);
                Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));

                if(b.isPresent()) {
                    Department department = new Department();
                    department.setName(departmentName);
                    department.setBranch(b.get());
                    Optional<Department> dp = this.allRepositories.findRepository("department").findOne(Example.of(department));
                    if(dp.isPresent()) {
                        obj.setDepartment(dp.get());
                    }else {
                        sb.append("department_id, ");
                        logger.warn("Department not found. Given department name : " + departmentName);
                    }
                }else {
                    sb.append("department_id, ");
                    logger.warn("Either branch name or branch address not provided, Cannot identify that given department belongs to which branch");
                }
            }
        }

        String sectionName = row.getCellAsString(41).orElse(null);
        if (CommonUtil.isNullOrEmpty(sectionName)) {
            sb.append("section_id,");
            logger.warn("Mandatory field missing. Field name - section_id");
        } else {
            Section se = new Section();
            if(SectionEnum.A.toString().equalsIgnoreCase(sectionName)){
                se.setSection(SectionEnum.A);
            }else if (SectionEnum.A.toString().equalsIgnoreCase(sectionName)){
                se.setSection(SectionEnum.B);
            }else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)){
                se.setSection(SectionEnum.C);
            }else if(SectionEnum.C.toString().equalsIgnoreCase(sectionName)) {
                se.setSection(SectionEnum.C);
            } else {
                sb.append("section_id, ");
                logger.warn("Given section not listed. Field name - section_name");
            }
        }

        String batchName = row.getCellAsString(40).orElse(null);
        if(CommonUtil.isNullOrEmpty(batchName)){
            sb.append("section_id,");
            logger.warn("Either batch name not provided, Cannot identify that given section belongs to which batch");
        }else {
            Batch batch = new Batch();
            if(BatchEnum.FIRSTYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.FIRSTYEAR);
            }else if(BatchEnum.SECONDYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.SECONDYEAR);
            }else if(BatchEnum.THIRDYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.THIRDYEAR);
            }else if(BatchEnum.FOURTHYEAR.toString().equalsIgnoreCase(batchName)) {
                batch.setBatch(BatchEnum.FOURTHYEAR);
            } else {
                sb.append("batch_id, ");
                logger.warn("Given batch not listed. Field name - batch_name");
            }
        }

        if(sb.length() > 0) {
            String msg = "Field name - ";
            throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
        }


        return (T) obj;
    }
}
