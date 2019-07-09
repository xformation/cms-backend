package com.synectiks.cms.graphql.types.Contract;

import com.synectiks.cms.domain.enumeration.TypeOfOwnerShip;

import java.util.Date;
import java.util.Objects;

public class AbstractContractInput {
    private Long id;
    private String vendorName;
    private TypeOfOwnerShip typeOfOwnerShip;
    private String durationOfContract;
    private Date startDate;
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractContractInput)) return false;
        AbstractContractInput that = (AbstractContractInput) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getVendorName(), that.getVendorName()) &&
            getTypeOfOwnerShip() == that.getTypeOfOwnerShip() &&
            Objects.equals(getDurationOfContract(), that.getDurationOfContract()) &&
            Objects.equals(getStartDate(), that.getStartDate()) &&
            Objects.equals(getEndDate(), that.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getVendorName(), getTypeOfOwnerShip(), getDurationOfContract(), getStartDate(), getEndDate());
    }

    @Override
    public String toString() {
        return "AbstractContractInput{" +
            "id=" + id +
            ", vendorName='" + vendorName + '\'' +
            ", typeOfOwnerShip=" + typeOfOwnerShip +
            ", durationOfContract='" + durationOfContract + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }
}
