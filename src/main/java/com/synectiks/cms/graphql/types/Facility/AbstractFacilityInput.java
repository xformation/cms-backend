package com.synectiks.cms.graphql.types.Facility;

import com.synectiks.cms.domain.enumeration.Status;

import java.util.Objects;

public class AbstractFacilityInput {
    private Long id;
    private Status transport;
    private Status mess;
    private Status gym;
    private Status culturalClass;
    private Status library;
    private Status sports;
    private Status swimming;
    private Status extraClass;
    private Status handicrafts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getTransport() {
        return transport;
    }

    public void setTransport(Status transport) {
        this.transport = transport;
    }

    public Status getMess() {
        return mess;
    }

    public void setMess(Status mess) {
        this.mess = mess;
    }

    public Status getGym() {
        return gym;
    }

    public void setGym(Status gym) {
        this.gym = gym;
    }

    public Status getCulturalClass() {
        return culturalClass;
    }

    public void setCulturalClass(Status culturalClass) {
        this.culturalClass = culturalClass;
    }

    public Status getLibrary() {
        return library;
    }

    public void setLibrary(Status library) {
        this.library = library;
    }

    public Status getSports() {
        return sports;
    }

    public void setSports(Status sports) {
        this.sports = sports;
    }

    public Status getSwimming() {
        return swimming;
    }

    public void setSwimming(Status swimming) {
        this.swimming = swimming;
    }

    public Status getExtraClass() {
        return extraClass;
    }

    public void setExtraClass(Status extraClass) {
        this.extraClass = extraClass;
    }

    public Status getHandicrafts() {
        return handicrafts;
    }

    public void setHandicrafts(Status handicrafts) {
        this.handicrafts = handicrafts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFacilityInput that = (AbstractFacilityInput) o;
        return Objects.equals(id, that.id) &&
            transport == that.transport &&
            mess == that.mess &&
            gym == that.gym &&
            culturalClass == that.culturalClass &&
            library == that.library &&
            sports == that.sports &&
            swimming == that.swimming &&
            extraClass == that.extraClass &&
            handicrafts == that.handicrafts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transport, mess, gym, culturalClass, library, sports, swimming, extraClass, handicrafts);
    }

    @Override
    public String toString() {
        return "AbstractFacilityInput{" +
            "id=" + id +
            ", transport=" + transport +
            ", mess=" + mess +
            ", gym=" + gym +
            ", culturalClass=" + culturalClass +
            ", library=" + library +
            ", sports=" + sports +
            ", swimming=" + swimming +
            ", extraClass=" + extraClass +
            ", handicrafts=" + handicrafts +
            '}';
    }
}
