package com.synectiks.cms.business.service;

import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.AcademicYearRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class SummaryDataService {


    @Autowired
    AcademicExamSettingRepository examSettingRepo;

    @Autowired
    AcademicYearRepository academicYearRepository;

    Date date = new Date();
    private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public List<ExamSettingPojo> getExams() {
        List<ExamSettingPojo> original = new ArrayList<>();
        examSettingRepo.findAll().forEach(examsetting -> {

            ExamSettingPojo settingModel = new ExamSettingPojo();
            BeanUtils.copyProperties(examsetting, settingModel);
            settingModel.setDepartmentId(examsetting.getDepartment().getId());
            settingModel.setSectionId(examsetting.getSection().getSection().name());
            settingModel.setSubject(examsetting.getSubject());
            settingModel.setAction(examsetting.getActions());
            settingModel.setExamDate(examsetting.getExamDate());
            settingModel.setEndDate(examsetting.getEndDate());
            settingModel.setStartDate(examsetting.getStartDate());

            original.add(settingModel);

            Date endDate = original.stream().map(ExamSettingPojo::getExamDate).max(Date::compareTo).get();
//            ExamSettingPojo ae = Collections.max(original, Comparator.comparing(ExamSettingPojo::getDate));

        });

        List<ExamSettingPojo> mergedList = new ArrayList<ExamSettingPojo>();
        for(ExamSettingPojo p : original) {
            int index = mergedList.indexOf(p);
            if(index != -1) {
                mergedList.set(index, mergedList.get(index).merge(p));
            } else {
                mergedList.add(p);
            }
        }
        System.out.println(mergedList);
        return  mergedList;
    }


}


