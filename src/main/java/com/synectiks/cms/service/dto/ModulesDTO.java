package com.synectiks.cms.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.synectiks.cms.domain.enumeration.Status;

/**
 * A DTO for the {@link com.synectiks.cms.domain.Modules} entity.
 */
public class ModulesDTO implements Serializable {

    private Long id;

    private String moduleName;

    private String subModuleName;

    private String url;

    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getSubModuleName() {
        return subModuleName;
    }

    public void setSubModuleName(String subModuleName) {
        this.subModuleName = subModuleName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModulesDTO modulesDTO = (ModulesDTO) o;
        if (modulesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modulesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModulesDTO{" +
            "id=" + getId() +
            ", moduleName='" + getModuleName() + "'" +
            ", subModuleName='" + getSubModuleName() + "'" +
            ", url='" + getUrl() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
