package com.synectiks.cms.graphql.types.course;

import com.synectiks.cms.graphql.types.gender.Gender;
import org.springframework.lang.Nullable;

public enum Course {

    BTECH(1, "BTECH"),
    MTECH(2, "MTECH"),
    BBA(3, "BBA"),
    MBA(4, "MBA");

    private final int value;
    private final String description;

    Course(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static Course valueOf(int courseId) {
        Course status = resolve(courseId);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + courseId + "]");
        }
        return status;
    }


    @Nullable
    public static Course resolve(int courseId) {
        for (Course status : values()) {
            if (status.value == courseId) {
                return status;
            }
        }
        return null;
    }

    @Nullable
    public static Course getCourseOnDescription(String courseDescription) {
        for (Course status : values()) {
            if (status.description.equalsIgnoreCase(courseDescription)) {
                return status;
            }
        }
        return null;
    }

    }
