package com.synectiks.cms.graphql.types.CompetitiveExam;

import java.util.List;

import com.synectiks.cms.entities.CompetitiveExam;

public class RemoveCompetitiveExamPayload {
    private final List<CompetitiveExam> CompetitiveExam;

    public List<CompetitiveExam> getCompetitiveExam() {
        return CompetitiveExam;
    }

    public RemoveCompetitiveExamPayload(List<CompetitiveExam> competitiveExam) {
        CompetitiveExam = competitiveExam;
    }
}
