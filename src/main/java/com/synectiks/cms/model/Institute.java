package com.synectiks.cms.model;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "institutes")
public class Institute extends BaseEntity {
    @Column(name = "code")
    @NotEmpty
    private String code;
    @Column(name = "name")
    @NotEmpty
    private String name;
    private Long year;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getYear(){
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId())
                .append("new", this.isNew())
                .append("name", this.getName())
                .append("code", this.getCode())
                .append("year", this.getYear())
                .toString();
    }
}


