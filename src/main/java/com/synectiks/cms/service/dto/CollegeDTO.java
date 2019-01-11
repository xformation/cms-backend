package com.synectiks.cms.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the College entity.
 */
public class CollegeDTO implements Serializable {

    private Long id;

    @NotNull
    private String shortName;

    @NotNull
    private Long logo;

    @NotNull
    private Long backgroundImage;

    @NotNull
    private String instructionInformation;

    private Long branchId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public Long getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Long backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getInstructionInformation() {
        return instructionInformation;
    }

    public void setInstructionInformation(String instructionInformation) {
        this.instructionInformation = instructionInformation;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CollegeDTO collegeDTO = (CollegeDTO) o;
        if (collegeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), collegeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CollegeDTO{" +
            "id=" + getId() +
            ", shortName='" + getShortName() + "'" +
            ", logo=" + getLogo() +
            ", backgroundImage=" + getBackgroundImage() +
            ", instructionInformation='" + getInstructionInformation() + "'" +
            ", branch=" + getBranchId() +
            "}";
    }
}
