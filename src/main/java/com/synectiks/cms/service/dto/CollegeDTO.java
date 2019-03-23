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
    private String logoPath;

    @NotNull
    private String backgroundImagePath;

    @NotNull
    private String instructionInformation;

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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public String getInstructionInformation() {
        return instructionInformation;
    }

    public void setInstructionInformation(String instructionInformation) {
        this.instructionInformation = instructionInformation;
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
            ", logoPath='" + getLogoPath() + "'" +
            ", backgroundImagePath='" + getBackgroundImagePath() + "'" +
            ", instructionInformation='" + getInstructionInformation() + "'" +
            "}";
    }
}
