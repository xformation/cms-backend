package com.synectiks.cms.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

//import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.utils.IESEntity;

/**
 * A Term.
 */
@Entity
@Table(name = "term")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
// @Document(indexName = "term")
public class Term implements Serializable, IESEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@NotNull
	@Column(name = "terms_desc", nullable = false)
	private String termsDesc;

	@NotNull
	@Column(name = "start_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate;

	@NotNull
	@Column(name = "end_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "term_status", nullable = false)
	private Status termStatus;

	@ManyToOne
	@JsonIgnoreProperties("terms")
	private AcademicYear academicyear;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTermsDesc() {
		return termsDesc;
	}

	public Term termsDesc(String termsDesc) {
		this.termsDesc = termsDesc;
		return this;
	}

	public void setTermsDesc(String termsDesc) {
		this.termsDesc = termsDesc;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Term startDate(LocalDate startDate) {
		this.startDate = startDate;
		return this;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Term endDate(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Status getTermStatus() {
		return termStatus;
	}

	public Term termStatus(Status termStatus) {
		this.termStatus = termStatus;
		return this;
	}

	public void setTermStatus(Status termStatus) {
		this.termStatus = termStatus;
	}

	public AcademicYear getAcademicyear() {
		return academicyear;
	}

	public Term academicyear(AcademicYear academicYear) {
		this.academicyear = academicYear;
		return this;
	}

	public void setAcademicyear(AcademicYear academicYear) {
		this.academicyear = academicYear;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters
	// and setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Term term = (Term) o;
		if (term.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), term.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "Term{" + "id=" + getId() + ", termsDesc='" + getTermsDesc() + "'"
				+ ", startDate='" + getStartDate() + "'" + ", endDate='" + getEndDate()
				+ "'" + ", termStatus='" + getTermStatus() + "'" + "}";
	}
}
