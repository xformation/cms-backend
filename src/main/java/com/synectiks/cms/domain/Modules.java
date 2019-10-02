package com.synectiks.cms.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

//import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

import com.synectiks.cms.domain.enumeration.Status;

/**
 * A Modules.
 */
@Entity
@Table(name = "modules")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@org.springframework.data.elasticsearch.annotations.Document(indexName = "modules")
public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    //@org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "sub_module_name")
    private String subModuleName;

    @Column(name = "url")
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Modules moduleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public Modules subModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
        return this;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public String getUrl() {
        return url;
    }

    public Modules url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public Modules status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Modules)) {
            return false;
        }
        return id != null && id.equals(((Modules) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Modules{" +
            "id=" + getId() +
            ", moduleName='" + getModuleName() + "'" +
            ", subModuleName='" + getSubModuleName() + "'" +
            ", url='" + getUrl() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
