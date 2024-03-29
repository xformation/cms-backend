package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A IdCard.
 */
@Entity
@Table(name = "id_card")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@Document(indexName = "idcard")
public class IdCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "jhi_desc", nullable = false)
    private String desc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public IdCard desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        IdCard idCard = (IdCard) o;
        if (idCard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), idCard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdCard{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
