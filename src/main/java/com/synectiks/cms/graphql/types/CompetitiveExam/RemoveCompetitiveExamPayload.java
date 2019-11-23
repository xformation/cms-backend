package com.synectiks.cms.graphql.types.CompetitiveExam;

import com.synectiks.commons.entities.cms.CompetitiveExam;

import java.util.List;

public class RemoveCompetitiveExamPayload {
    private final List<CompetitiveExam> CompetitiveExam;

    public List<CompetitiveExam> getCompetitiveExam() {
        return CompetitiveExam;
    }

    public RemoveCompetitiveExamPayload(List<CompetitiveExam> competitiveExam) {
        CompetitiveExam = competitiveExam;
    }
}
