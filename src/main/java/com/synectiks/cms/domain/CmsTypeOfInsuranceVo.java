package com.synectiks.cms.domain;

import java.io.Serializable;

public class CmsTypeOfInsuranceVo implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -535057197989469058L;
    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 41;
        int result = 41;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CmsTypeOfInsuranceVo other = (CmsTypeOfInsuranceVo) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CmsTypeOfInsuranceVo{" +
            "id=" + id +
            ", description='" + description + '\'' +
            '}';
    }
}
