package com.synectiks.cms.business.service.exam;

import com.synectiks.cms.business.service.exam.AcExamSetting;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SummaryDataService {

    @Autowired
    AcademicExamSettingRepository examSettingRepo;

    @Autowired
    AcademicYearRepository academicYearRepository;

    public List<AcExamSetting> acExamSettings() {
        List<AcExamSetting> original = new ArrayList<>();
        examSettingRepo.findAll().forEach(examsetting -> {

            AcExamSetting settingModel = new AcExamSetting();
            BeanUtils.copyProperties(examsetting, settingModel);
            System.out.println("ONE : "+ examsetting.getId());
            System.out.println("TWO : "+ settingModel.getId());
            settingModel.setDepartmnt(examsetting.getDepartment().getName());
            settingModel.setBctch(examsetting.getBatch().getBatch().name());
            settingModel.setSectn(examsetting.getSection().getSection().name());
            settingModel.setBrnch(examsetting.getBranch().getBranchName());
            settingModel.setSbjct(examsetting.getSubject().getSubjectDesc());
            //settingModel.setId(examsetting.getSubject().getId());
            settingModel.setAction(examsetting.getSubject().getId().toString());
            settingModel.setEndDate(DateFormatUtil.converUtilDateFromLocaDate(examsetting.getEndDate()));
            settingModel.setStartDate(DateFormatUtil.converUtilDateFromLocaDate(examsetting.getStartDate()));
            settingModel.setExamDate(DateFormatUtil.converUtilDateFromLocaDate(examsetting.getExamDate()));
            settingModel.setSubExamDate(examsetting.getExamDate().toString());
            settingModel.setSt(examsetting.getExamDate().toString());
            settingModel.setEd(examsetting.getExamDate().toString());
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

        Optional<AcademicExamSetting> ain = examSettingRepo.findById(id);
        AcExamSetting aimodel = new AcExamSetting();
        BeanUtils.copyProperties(ain, aimodel);

        aimodel.setDepartmnt(ain.get().getDepartment().getName());
        aimodel.setExamDate(DateFormatUtil.converUtilDateFromLocaDate(ain.get().getExamDate()));
        aimodel.setBctch(ain.get().getBatch().getBatch().name());
        aimodel.setSectn(ain.get().getSection().getSection().name());
        aimodel.setBrnch(ain.get().getBranch().getBranchName());
        aimodel.setSbjct(ain.get().getSubject().getSubjectDesc());
        aimodel.setExamType(ain.get().getExamType());
        aimodel.setEd(ain.get().getActions());
        aimodel.setSt(ain.get().getActions());
        aimodel.setAction(ain.get().getActions());
        aimodel.setStartDate(DateFormatUtil.converUtilDateFromLocaDate(ain.get().getStartDate()));
        aimodel.setEndDate(DateFormatUtil.converUtilDateFromLocaDate(ain.get().getEndDate()));
        return aimodel;

    }

//    public AcExamSetting acExamSetting(String action) {
//       academicYearRepository.find
//    }



}


