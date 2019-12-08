package com.synectiks.cms.entities.enumeration;

import org.springframework.lang.Nullable;

import com.synectiks.cms.entities.Section;

/**
 * The CmsSectionEnum enumeration.
 */
public enum CmsSectionEnum {
    
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
	E(5, "E"),
	F(6, "F"),
	G(7, "G"),
	H(8, "H"),
	I(9, "I"),
	J(10, "J");
    
	
    private final int value;
	private final String description;
	
	CmsSectionEnum(int value, String description){
		this.value = value;
		this.description = description;
	}
	
	public int value() {
		return this.value;
	}
	
	public Long id() {
		return new Long(this.value);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
	public static CmsSectionEnum findById(int id) {
		CmsSectionEnum cmsSectionEnum = resolve(id);
		if (cmsSectionEnum == null) {
			throw new IllegalArgumentException("No matching constant for [" + id + "]");
		}
		return cmsSectionEnum;
	}
	
	public static CmsSectionEnum findByDescription(String description) {
		CmsSectionEnum cmsSectionEnum = resolve(description);
		if (cmsSectionEnum == null) {
			throw new IllegalArgumentException("No matching constant for [" + description + "]");
		}
		return cmsSectionEnum;
	}
	
	public static Section createSection(CmsSectionEnum sEnum) {
		Section section = new Section();
		section.setId(sEnum.id());
		if(sEnum.id() == 1) {
			section.setSection(SectionEnum.A);
		}else if(sEnum.id() == 2) {
			section.setSection(SectionEnum.B);
		}else if(sEnum.id() == 3) {
			section.setSection(SectionEnum.C);
		}else if(sEnum.id() == 4) {
			section.setSection(SectionEnum.D);
		}
		
		return section;
	}
	
	@Nullable
	private static CmsSectionEnum resolve(int id) {
		for (CmsSectionEnum cmsSectionEnum : values()) {
			if (cmsSectionEnum.value == id) {
				return cmsSectionEnum;
			}
		}
		return null;
	}
	
	@Nullable
	private static CmsSectionEnum resolve(String description) {
		for (CmsSectionEnum cmsSectionEnum : values()) {
			if (cmsSectionEnum.description == description) {
				return cmsSectionEnum;
			}
		}
		return null;
	}
	
}
