package com.synectiks.cms.graphql.types.College;

import java.util.Objects;

public class AbstractCollegeInput {
    private Long id;
    private String shortName;
    private Long logo;
    private Long backgroundImage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCollegeInput that = (AbstractCollegeInput) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(shortName, that.shortName) &&
            Objects.equals(logo, that.logo) &&
            Objects.equals(backgroundImage, that.backgroundImage) &&
            Objects.equals(instructionInformation, that.instructionInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shortName, logo, backgroundImage, instructionInformation);
    }

    @Override
    public String toString() {
        return "AbstractCollegeInput{" +
            "id=" + id +
            ", shortName='" + shortName + '\'' +
            ", logo=" + logo +
            ", backgroundImage=" + backgroundImage +
            ", instructionInformation='" + instructionInformation + '\'' +
            '}';
    }
}
