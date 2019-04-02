package com.synectiks.cms.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A College.
 */
@Entity
@Table(name = "college")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "college")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "short_name", nullable = false)
    private String shortName;

    @Column(name = "logo_path")
    private String logoPath;

    @Column(name = "background_image_path")
    private String backgroundImagePath;

    @Column(name = "instruction_information")
    private String instructionInformation;

    @Column(name = "logo_file_name")
    private String logoFileName;

    @Column(name = "background_image_file_name")
    private String backgroundImageFileName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public College shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public College logoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public College backgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
        return this;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public String getInstructionInformation() {
        return instructionInformation;
    }

    public College instructionInformation(String instructionInformation) {
        this.instructionInformation = instructionInformation;
        return this;
    }

    public void setInstructionInformation(String instructionInformation) {
        this.instructionInformation = instructionInformation;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public College logoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
        return this;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getBackgroundImageFileName() {
        return backgroundImageFileName;
    }

    public College backgroundImageFileName(String backgroundImageFileName) {
        this.backgroundImageFileName = backgroundImageFileName;
        return this;
    }

    public void setBackgroundImageFileName(String backgroundImageFileName) {
        this.backgroundImageFileName = backgroundImageFileName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        College college = (College) o;
        if (college.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), college.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "College{" +
            "id=" + getId() +
            ", shortName='" + getShortName() + "'" +
            ", logoPath='" + getLogoPath() + "'" +
            ", backgroundImagePath='" + getBackgroundImagePath() + "'" +
            ", instructionInformation='" + getInstructionInformation() + "'" +
            ", logoFileName='" + getLogoFileName() + "'" +
            ", backgroundImageFileName='" + getBackgroundImageFileName() + "'" +
            "}";
    }
}
