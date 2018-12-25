package com.synectiks.cms.graphql.types.Institute;

public class AbstractInstituteInput {
    private String name;
    private String code;
    private Long year;

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

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

    @Override
    public String toString(){
        return "AbstractInstituteInput{"+
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", year='" + year + '\'' +
                '}';

    }

}
