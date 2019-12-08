package com.synectiks.cms.graphql.types.CompetitiveExam;

import com.synectiks.cms.entities.CompetitiveExam;
import com.synectiks.cms.entities.Student;

public class AbstractCompetitiveExamPayload {

    private final CompetitiveExam competitiveExam;

    public CompetitiveExam getCompetitiveExam() {
        return competitiveExam;
    }

    public AbstractCompetitiveExamPayload(CompetitiveExam competitiveExam) {
        this.competitiveExam = competitiveExam;
    }
}

