package com.synectiks.cms.graphql.types.AdmissionEnquiry;

import java.util.List;

import com.synectiks.cms.entities.AdmissionEnquiry;

public class RemoveAdmissionEnquiryPayload {

    private final List<AdmissionEnquiry> admissionEnquiries;

    public RemoveAdmissionEnquiryPayload(List<AdmissionEnquiry> admissionEnquiries){
        this.admissionEnquiries = admissionEnquiries;
    }
    public List<AdmissionEnquiry> getAdmissionEnquiries(){
        return admissionEnquiries;
    }
}
