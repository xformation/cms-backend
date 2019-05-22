package com.synectiks.cms.graphql.types.Student;

import org.springframework.lang.Nullable;

/**
 * The StudentType enumeration.
 */
public enum StudentType {
	
	REGULAR(1, "REGULAR"),
	STAFF_CONCESSION(2, "STAFF_CONCESSION"),
	BENEFITS(3, "BENEFITS"),
	SCHOLARSHIP(4, "SCHOLARSHIP"),
	OTHER_BENEFITS(5, "OTHER_BENEFITS");
    
    private final int value;
	private final String description;
	
	StudentType(int value, String description){
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
	
	public static StudentType valueOf(int studentTypeCode) {
		StudentType status = resolve(studentTypeCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + studentTypeCode + "]");
		}
		return status;
	}
	
		
	@Nullable
	public static StudentType resolve(int studentTypeCode) {
		for (StudentType status : values()) {
			if (status.value == studentTypeCode) {
				return status;
			}
		}
		return null;
	}
	
	@Nullable
	public static StudentType getStudentTypeOnDescription(String studentTypeDescription) {
		for (StudentType status : values()) {
			if (status.description.equalsIgnoreCase(studentTypeDescription)) {
				return status;
			}
		}
		return null;
	}
	
}
