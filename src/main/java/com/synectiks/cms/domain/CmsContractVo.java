package com.synectiks.cms.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.synectiks.cms.domain.enumeration.TypeOfOwnerShip;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CmsContractVo extends CmsCommonVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String vendorName;
    private TypeOfOwnerShip typeOfOwnerShip;
    private String durationOfContract;
    private LocalDate startDate;
    private LocalDate endDate;
    private String strStartDate;
    private String strEndDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)

    private List<CmsContractVo> dataList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(String durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    @Override
    public TypeOfOwnerShip getTypeOfOwnerShip() {
        return typeOfOwnerShip;
    }
    @Override
    public void setTypeOfOwnerShip(TypeOfOwnerShip typeOfOwnerShip) {
        this.typeOfOwnerShip = typeOfOwnerShip;
    }


    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<CmsContractVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CmsContractVo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CmsContractVo{" +
            "id=" + id +
            ", vendorName='" + vendorName + '\'' +
            ", typeOfOwnerShip=" + typeOfOwnerShip +
            ", durationOfContract='" + durationOfContract + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", strStartDate='" + strStartDate + '\'' +
            ", strEndDate='" + strEndDate + '\'' +
            ", dataList=" + dataList +
            '}';
    }
}
