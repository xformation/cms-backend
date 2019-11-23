package com.synectiks.cms.graphql.types.AdmissionEnquiry;

import com.synectiks.commons.entities.cms.AdmissionEnquiry;

import java.util.List;

public class RemoveAdmissionEnquiryPayload {

    private final List<AdmissionEnquiry> admissionEnquiries;

    public RemoveAdmissionEnquiryPayload(List<AdmissionEnquiry> admissionEnquiries){
        this.admissionEnquiries = admissionEnquiries;
    }
    public List<AdmissionEnquiry> getAdmissionEnquiries(){
        return admissionEnquiries;
    }
}
