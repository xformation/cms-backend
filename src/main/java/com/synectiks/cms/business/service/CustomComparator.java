package com.synectiks.cms.business.service;

import java.util.*;

public class CustomComparator implements Comparator<AcExamSetting> {

    @Override
    public int compare(AcExamSetting p, AcExamSetting q) {
        if (p.getExamDate().before(q.getExamDate()))
        {
            return -1;
        } else if (p.getExamDate().after(q.getExamDate())) {
            return 1;
        } else {
            return 0;
        }
    }



}
