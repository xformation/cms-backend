package com.synectiks.cms.graphql.types.SignatoryLink;

public class AbstractSignatoryLinkInput {
    private Long id;
    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "AbstractSignatoryLinkInput{" +
            "id=" + id +
            ", desc='" + desc + '\'' +
            '}';
    }
}
