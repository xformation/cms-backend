package com.synectiks.cms.filter.exam;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.domain.enumeration.SemesterEnum;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceUpdateFilter;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AcademicExamSettingFilterImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AcademicYearRepository academicYearRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AcademicExamSettingRepository academicExamSettingRepository;


    @Transactional
    public QueryResult updateExamStatus(List<AcademicExamSettingUpdateFilter> list) {
        logger.info("Start updating student attendance data ");
        QueryResult result = new QueryResult();
        result.setStatusCode(0);
        result.setStatusDesc(CmsConstants.UPDATE_SUCCESS_MESSAGE);

        AcademicExamSetting stObj = new AcademicExamSetting();
        Department department = null;
        AcademicYear academicyear = null;
        Section section = null;
        Batch batch = null;
        try {
            for(AcademicExamSettingUpdateFilter sa: list) {
                String dep = sa.getDepartmentId();//.split("##delimline##");
                String acyear = sa.getAcademicyearId();
                String sc = sa.getSectionId();
                String batches = sa.getBatchId();
                if(academicyear == null) {
                    academicyear = this.academicYearRepository.findById(Long.valueOf(acyear)).get();
                }
                if(department == null){
                    department = this.departmentRepository.findById(Long.valueOf(dep)).get();
                }
                if(section == null){
                    section= this.sectionRepository.findById(Long.valueOf(sc)).get();
                }
                if(batch == null){
                    batch= this.batchRepository.findById(Long.valueOf(batches)).get();
                }

                String data[] = dep.split("#~#");
                department = this.departmentRepository.findById(Long.valueOf(data[0])).get();

                stObj.setDepartment(department);
                stObj.setAcademicyear(academicyear);
                stObj.setSection(section);
                stObj.setBatch(batch);
                Example<AcademicExamSetting> example = Example.of(stObj);
                Optional<AcademicExamSetting> osa = this.academicExamSettingRepository.findOne((example));
                if(osa.isPresent()) {
                    stObj.setId(osa.get().getId());
                    stObj.setSemester(data[1].equalsIgnoreCase("SEMESTER1") ? SemesterEnum.SEMESTER1 : SemesterEnum.SEMESTER2);
                    if(data.length > 2) {
                        stObj.day(data[2]);
                    }else {
                        stObj.startTime(null);
                    }
                    logger.debug("Updating exam id : "+osa.get().getId());
                    this.academicExamSettingRepository.save(stObj);
                    stObj.setId(null);
                    stObj.setSemester(null);
                    stObj.setStartTime(null);
                }
//        		}
            }
        }catch(Exception e) {
            logger.error("Method updateExamStatus. "+CmsConstants.UPDATE_FAILURE_MESSAGE, e);
            result.setStatusCode(1);
            result.setStatusDesc(CmsConstants.UPDATE_FAILURE_MESSAGE);
        }

        return result;
    }

}
