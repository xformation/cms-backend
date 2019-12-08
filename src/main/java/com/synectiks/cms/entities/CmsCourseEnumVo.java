package com.synectiks.cms.entities;

import java.io.Serializable;

public class CmsCourseEnumVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1065999859387768338L;
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
        final int prime = 47;
        int result = 47;
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
        CmsCourseEnumVo other = (CmsCourseEnumVo) obj;
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
        return "CmsCourseEnumVo [id=" + id + ", description=" + description + "]";
    }





}
