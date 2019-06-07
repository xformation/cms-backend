package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.AcademicExamSetting;
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

    public List<AcExamSetting> acExamSettings() {
        List<AcExamSetting> original = new ArrayList<>();
        examSettingRepo.findAll().forEach(examsetting -> {

            AcExamSetting settingModel = new AcExamSetting();
            BeanUtils.copyProperties(examsetting, settingModel);
            settingModel.setDepartmnt(examsetting.getDepartment().getName());
            settingModel.setSectn(examsetting.getSection().getSection().name());
            settingModel.setSubject(examsetting.getSubject());
            settingModel.setAction(examsetting.getActions());
            settingModel.setEndDate(examsetting.getEndDate());
            settingModel.setStartDate(examsetting.getStartDate());
            settingModel.setEndDate(examsetting.getExamDate());

            original.add(settingModel);


        });

        List<AcExamSetting> mergedList = new ArrayList<AcExamSetting>();
        for(AcExamSetting p : original) {
            int index = mergedList.indexOf(p);
            if(index != -1) {
                mergedList.set(index, mergedList.get(index).merge(p));
            } else {
                mergedList.add(p);
            }
        }
        
        return  mergedList;
    }



    public AcExamSetting acExamSetting(Long id) {

        Optional<AcademicExamSetting> ain = examSettingRepo.findById(id);
        AcExamSetting aimodel = new AcExamSetting();
        BeanUtils.copyProperties(ain, aimodel);

        aimodel.setDepartmnt(ain.get().getDepartment().getName());
        aimodel.setAction(ain.get().getActions());

        return aimodel;


    }




}


