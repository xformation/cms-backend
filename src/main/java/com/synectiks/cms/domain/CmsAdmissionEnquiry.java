package com.synectiks.cms.domain;

public class CmsAdmissionEnquiry {
    private Long totalAdmissions = 0L;
    private Long totalFollowup = 0L;
    private Long totalDeclined = 0L;
    private Long totalConverted = 0L;

    public Long getTotalAdmissions() {
        return totalAdmissions;
    }

    public void setTotalAdmissions(Long totalAdmissions) {
        this.totalAdmissions = totalAdmissions;
    }

    public Long getTotalFollowup() {
        return totalFollowup;
    }

    public void setTotalFollowup(Long totalFollowup) {
        this.totalFollowup = totalFollowup;
    }

    public Long getTotalDeclined() {
        return totalDeclined;
    }

    public void setTotalDeclined(Long totalDeclined) {
        this.totalDeclined = totalDeclined;
    }

    public Long getTotalConverted() {
        return totalConverted;
    }

    public void setTotalConverted(Long totalConverted) {
        this.totalConverted = totalConverted;
    }
}
