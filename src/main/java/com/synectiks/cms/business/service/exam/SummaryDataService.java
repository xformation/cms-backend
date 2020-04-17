package com.synectiks.cms.business.service.exam;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.TypeOfGradingRepository;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class SummaryDataService {

    @Autowired
    AcademicExamSettingRepository academicExamSettingRepository;

//    @Autowired
//    AcademicYearRepository academicYearRepository;

    @Autowired
    TypeOfGradingRepository typeOfGradingRepository;

//    @Autowired
//    SubjectRepository subjectRepository;

//    @Autowired
//    BatchRepository batchRepository;

//    @Autowired
//    SectionRepository sectionRepository;

//    @Autowired
//    DepartmentRepository departmentRepository;

//    @Autowired
//    BranchRepository branchRepository;

    @Autowired
    CommonService commonService;

    public List<AcExamSetting> acExamSettings() {
        List<AcExamSetting> original = new ArrayList<>();
        academicExamSettingRepository.findAll().forEach(examsetting -> {

            AcExamSetting settingModel = new AcExamSetting();
            BeanUtils.copyProperties(examsetting, settingModel);
            System.out.println("ONE : "+ examsetting.getId());
            System.out.println("TWO : "+ settingModel.getId());
            Department department = this.commonService.getDepartmentById(examsetting.getDepartmentId());
            settingModel.setDepartmnt((department != null) ? department.getName() : null);
            Batch batch = this.commonService.getBatchById(examsetting.getBatchId());
            settingModel.setBctch(batch != null ? batch.getBatch().name() : null);
            Section section = this.commonService.getSectionById(examsetting.getSectionId());
            settingModel.setSectn(section != null ? section.getSection().name() : null);
            Branch branch = this.commonService.getBranchById(examsetting.getBranchId());
            settingModel.setBrnch(branch != null ? branch.getBranchName() : null);
            Subject subject = this.commonService.getSubjectById(examsetting.getSubjectId());
            settingModel.setSbjct(subject != null ? subject.getSubjectDesc() : null);
            //settingModel.setId(examsetting.getSubject().getId());
            settingModel.setAction(subject != null ? subject.getId().toString() : "");
            settingModel.setExamDate(DateFormatUtil.converUtilDateFromLocaDate(examsetting.getExamDate()));
            settingModel.setSubExamDate(examsetting.getExamDate().toString());
            settingModel.setSt(examsetting.getExamDate().toString());
            settingModel.setEd(examsetting.getExamDate().toString());
            settingModel.setTotal(examsetting.getTotal());
            settingModel.setPassing(examsetting.getPassing());
            settingModel.setStartTime(examsetting.getStartTime());
            settingModel.setEndTime(examsetting.getEndTime());
            settingModel.setSemester(examsetting.getSemester());
            original.add(settingModel);
            original.sort(Comparator.comparing(AcExamSetting::getExamDate));
        });

        List<AcExamSetting> mergedList = new ArrayList<AcExamSetting>();
        for (AcExamSetting p : original) {
            int index = mergedList.indexOf(p);

            if (index != -1) {
                mergedList.set(index, mergedList.get(index).merge(p));
            } else {
                mergedList.add(p);
            }
        }
        return mergedList;
    }


    public AcExamSetting acExamSetting(Long id) {

        Optional<AcademicExamSetting> ain = academicExamSettingRepository.findById(id);
        AcExamSetting aimodel = new AcExamSetting();
        BeanUtils.copyProperties(ain, aimodel);

        Department department = this.commonService.getDepartmentById(ain.get().getDepartmentId());
        Batch batch = this.commonService.getBatchById(ain.get().getBatchId());
        Section section = this.commonService.getSectionById(ain.get().getSectionId());
        Branch branch = this.commonService.getBranchById(ain.get().getBranchId());
        Subject subject = this.commonService.getSubjectById(ain.get().getSubjectId());

        aimodel.setDepartmnt(department.getName());
        aimodel.setExamDate(DateFormatUtil.converUtilDateFromLocaDate(ain.get().getExamDate()));
        aimodel.setBctch(batch.getBatch().name());
        aimodel.setSectn(section.getSection().name());
        aimodel.setBrnch(branch.getBranchName());
        aimodel.setSbjct(subject.getSubjectDesc());
        aimodel.setExamName(ain.get().getExamName());
        aimodel.setEd(ain.get().getActions());
        aimodel.setSt(ain.get().getActions());
        aimodel.setAction(ain.get().getActions());
        aimodel.setTotal(ain.get().getTotal());
        aimodel.setPassing(ain.get().getPassing());
        aimodel.setStartTime(ain.get().getStartTime());
        aimodel.setEndTime(ain.get().getEndTime());
        aimodel.setSemester(ain.get().getSemester());
        return aimodel;

    }

    public List<AcademicExamSetting> findExamValuesOnGroupvalue(Long countvalue){
        List<AcademicExamSetting> original = new ArrayList<>();
        academicExamSettingRepository.findAll().forEach(exams -> {
//            Branch branch = branchRepository.findById(original.getBranchId()).get();
            AcademicExamSetting settingModel = new AcademicExamSetting();
            BeanUtils.copyProperties(exams, settingModel);
            settingModel.setActions(exams.getExamDate().toString());

            original.add(settingModel);
        });
        return original;
    }
    public List<TypeOfGrading> findTypeOfGradingOnNextId(Long groupvalue){
        List<TypeOfGrading> original = new ArrayList<>();
        typeOfGradingRepository.findAll().forEach(typeofgrading -> {
            TypeOfGrading settingModel = new TypeOfGrading();
            BeanUtils.copyProperties(typeofgrading, settingModel);
            original.add(settingModel);
        });
        return original;
    }

}
