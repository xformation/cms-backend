package com.synectiks.cms.graphql.types.gender;

import org.springframework.lang.Nullable;

/**
 * The Gender enumeration.
 */
public enum Gender {
	
	MALE(1, "MALE"),
	FEMALE(2, "FEMALE"),
	OTHER(3, "OTHER");
    
    private final int value;
	private final String description;
	
	Gender(int value, String description){
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
	
	public static Gender valueOf(int genderId) {
		Gender status = resolve(genderId);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + genderId + "]");
		}
		return status;
	}
	
		
	@Nullable
	public static Gender resolve(int genderId) {
		for (Gender status : values()) {
			if (status.value == genderId) {
				return status;
			}
		}
		return null;
	}
	
	@Nullable
	public static Gender getGenderOnDescription(String genderDescription) {
		for (Gender status : values()) {
			if (status.description.equalsIgnoreCase(genderDescription)) {
				return status;
			}
		}
		return null;
	}
	
}
