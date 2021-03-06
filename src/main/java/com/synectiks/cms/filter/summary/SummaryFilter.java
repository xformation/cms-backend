package com.synectiks.cms.filter.summary;

import com.synectiks.cms.business.service.exam.AcExamSetting;
import com.synectiks.cms.business.service.exam.ExamReportFilterInput;
import com.synectiks.cms.business.service.exam.SummaryDataService;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.domain.TypeOfGrading;
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
    public List<TypeOfGrading> findTypeOfGradingOnNextId(Long groupvalue){return summaryDataService.findTypeOfGradingOnNextId(groupvalue);}
    public List<AcademicExamSetting> findExamValuesOnGroupvalue(Long countvalue){return summaryDataService.findExamValuesOnGroupvalue(countvalue);}

}




