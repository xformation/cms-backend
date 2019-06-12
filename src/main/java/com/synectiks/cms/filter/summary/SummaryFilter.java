package com.synectiks.cms.filter.summary;

import com.synectiks.cms.business.service.exam.AcExamSetting;
import com.synectiks.cms.business.service.exam.SummaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummaryFilter {

     @Autowired
    SummaryDataService summaryDataService;

    public List<AcExamSetting> acExamSettings(){
       return summaryDataService.acExamSettings();
    }
    public AcExamSetting acExamSetting(Long id){
        return summaryDataService.acExamSetting(id);
    } ;


}




