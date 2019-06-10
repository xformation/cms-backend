package com.synectiks.cms.business.service;

import java.util.*;

public class CustomComparator implements Comparator<AcExamSetting> {

    @Override
    public int compare(AcExamSetting a, AcExamSetting b)
    {
        return a.getExamDate().compareTo(b.getExamDate());
    }



}
