package com.synectiks.cms.graphql.types.AcademicHistory;

import com.synectiks.commons.entities.cms.AcademicHistory;

public class UpdateAcademicHistoryPayload extends AddAcademicHistoryPayload {
    public UpdateAcademicHistoryPayload(AcademicHistory academicHistory)
    {
        super(academicHistory);
    }
}
