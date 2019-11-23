package com.synectiks.cms.graphql.types.CompetitiveExam;

import com.synectiks.commons.entities.cms.CompetitiveExam;
import com.synectiks.commons.entities.cms.Student;

public class AbstractCompetitiveExamPayload {

    private final CompetitiveExam competitiveExam;

    public CompetitiveExam getCompetitiveExam() {
        return competitiveExam;
    }

    public AbstractCompetitiveExamPayload(CompetitiveExam competitiveExam) {
        this.competitiveExam = competitiveExam;
    }
}

