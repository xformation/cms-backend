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

    @NotNull
    @Column(name = "logo", nullable = false)
    private Long logo;

    @NotNull
    @Column(name = "background_image", nullable = false)
    private Long backgroundImage;

    @NotNull
    @Column(name = "instruction_information", nullable = false)
    private String instructionInformation;

    @OneToOne
    @JoinColumn(unique = true)
    private Branch branch;

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

    public Long getLogo() {
        return logo;
    }

    public College logo(Long logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public Long getBackgroundImage() {
        return backgroundImage;
    }

    public College backgroundImage(Long backgroundImage) {
        this.backgroundImage = backgroundImage;
        return this;
    }

    public void setBackgroundImage(Long backgroundImage) {
        this.backgroundImage = backgroundImage;
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

    public Branch getBranch() {
        return branch;
    }

    public College branch(Branch branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
            ", logo=" + getLogo() +
            ", backgroundImage=" + getBackgroundImage() +
            ", instructionInformation='" + getInstructionInformation() + "'" +
            "}";
    }
}
