package com.synectiks.cms.graphql.types.CompetitiveExam;

import com.synectiks.cms.domain.CompetitiveExam;
import com.synectiks.cms.domain.Student;

public class AbstractCompetitiveExamPayload {

    private final CompetitiveExam competitiveExam;

    public CompetitiveExam getCompetitiveExam() {
        return competitiveExam;
    }

    public AbstractCompetitiveExamPayload(CompetitiveExam competitiveExam) {
        this.competitiveExam = competitiveExam;
    }
}

