package com.synectiks.cms.domain;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.TypeOfInsurance;
//import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsInsuranceVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Vehicle vehicle;
    private Long vehicleId;
    private String insuranceCompany;
    private TypeOfInsurance typeOfInsurance;
    private String strDateOfInsurance;
    private String strValidTill;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfInsurance;
    private LocalDate validTill;
    private List<CmsInsuranceVo> dataList = new ArrayList<>();

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public TypeOfInsurance getTypeOfInsurance() {
        return typeOfInsurance;
    }

    @Override
    public void setTypeOfInsurance(TypeOfInsurance typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public String getStrDateOfInsurance() {
        return strDateOfInsurance;
    }

    public void setStrDateOfInsurance(String strDateOfInsurance) {
        this.strDateOfInsurance = strDateOfInsurance;
    }

    public String getStrValidTill() {
        return strValidTill;
    }

    public void setStrValidTill(String strValidTill) {
        this.strValidTill = strValidTill;
    }

    public LocalDate getDateOfInsurance() {
        return dateOfInsurance;
    }

    public void setDateOfInsurance(LocalDate dateOfInsurance) {
        this.dateOfInsurance = dateOfInsurance;
    }

    public LocalDate getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDate validTill) {
        this.validTill = validTill;
    }

    public List<CmsInsuranceVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsInsuranceVo> dataList) {
        this.dataList = dataList;
    }



    @Override
    public String toString() {
        return "CmsInsuranceVo{" +
            "id=" + id +
            ", vehicle=" + vehicle +
            ", vehicleId=" + vehicleId +
            ", insuranceCompany='" + insuranceCompany + '\'' +
            ", typeOfInsurance=" + typeOfInsurance +
            ", strDateOfInsurance='" + strDateOfInsurance + '\'' +
            ", strValidTill='" + strValidTill + '\'' +
            ", dateOfInsurance=" + dateOfInsurance +
            ", validTill=" + validTill +
            ", dataList=" + dataList +
            '}';
    }
}
