package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A DTO for the Facility entity.
 */
public class FacilityDTO implements Serializable {

    private Long id;

    private Status transport;

    private Status mess;

    private Status gym;

    private Status culturalClass;

    private Status library;

    private Status sports;

    private Status swimming;

    private Status extraClass;

    private Status handicrafts;


    private Long academicYearId;

    private Long branchId;

    private Long studentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getTransport() {
        return transport;
    }

    public void setTransport(Status transport) {
        this.transport = transport;
    }

    public Status getMess() {
        return mess;
    }

    public void setMess(Status mess) {
        this.mess = mess;
    }

    public Status getGym() {
        return gym;
    }

    public void setGym(Status gym) {
        this.gym = gym;
    }

    public Status getCulturalClass() {
        return culturalClass;
    }

    public void setCulturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
    }

    public Status getLibrary() {
        return library;
    }

    public void setLibrary(Status library) {
        this.library = library;
    }

    public Status getSports() {
        return sports;
    }

    public void setSports(Status sports) {
        this.sports = sports;
    }

    public Status getSwimming() {
        return swimming;
    }

    public void setSwimming(Status swimming) {
        this.swimming = swimming;
    }

    public Status getExtraClass() {
        return extraClass;
    }

    public void setExtraClass(Status extraClass) {
        this.extraClass = extraClass;
    }

    public Status getHandicrafts() {
        return handicrafts;
    }

    public void setHandicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacilityDTO facilityDTO = (FacilityDTO) o;
        if (facilityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facilityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FacilityDTO{" +
            "id=" + getId() +
            ", transport='" + getTransport() + "'" +
            ", mess='" + getMess() + "'" +
            ", gym='" + getGym() + "'" +
            ", culturalClass='" + getCulturalClass() + "'" +
            ", library='" + getLibrary() + "'" +
            ", sports='" + getSports() + "'" +
            ", swimming='" + getSwimming() + "'" +
            ", extraClass='" + getExtraClass() + "'" +
            ", handicrafts='" + getHandicrafts() + "'" +
            ", academicYear=" + getAcademicYearId() +
            ", branch=" + getBranchId() +
            ", student=" + getStudentId() +
            "}";
    }
}
