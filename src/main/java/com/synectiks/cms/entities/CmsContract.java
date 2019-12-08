package com.synectiks.cms.entities;

import java.time.LocalDate;

import com.synectiks.cms.entities.enumeration.TypeOfOwnerShip;

public class CmsContract  implements Comparable<CmsContract> {
    private Long id;
    private String vendorName;
    private TypeOfOwnerShip typeOfOwnerShip;
    private String durationOfContract;
    private LocalDate startDate;
    private LocalDate endDate;
    private String strStartDate;
    private String strEndDate;

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

    public TypeOfOwnerShip getTypeOfOwnerShip() {
        return typeOfOwnerShip;
    }

    public void setTypeOfOwnerShip(TypeOfOwnerShip typeOfOwnerShip) {
        this.typeOfOwnerShip = typeOfOwnerShip;
    }

    public String getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(String durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    @Override
    public int compareTo(CmsContract o) {
        return this.id.compareTo(o.getId());
    }
}
