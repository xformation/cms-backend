package com.synectiks.cms.graphql.types.Student;

import org.springframework.lang.Nullable;

/**
 * The Semester enumeration.
 */
public enum Semester {
	
    FIRST(1, "FIRST"),
	SECOND(2, "SECOND"),
	THIRD(3, "THIRD"),
	FOURTH(4, "FOURTH"),
	FIFTH(5, "FIFTH"),
	SIXTH(6, "SIXTH"),
	SEVENTH(7, "SEVENTH"),
	EIGHTH(8, "EIGHTH");
    
    private final int value;
	private final String description;
	
	Semester(int value, String description){
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
	
	public static Semester valueOf(int statusCode) {
		Semester status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}
	
	@Nullable
	public static Semester resolve(int statusCode) {
		for (Semester status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}
	
}
