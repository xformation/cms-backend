package com.synectiks.cms.entities.enumeration;

import org.springframework.lang.Nullable;

import com.synectiks.cms.entities.Batch;

/**
 * The CmsBatchEnum enumeration.
 */
public enum CmsBatchEnum {
    
	FIRSTYEAR(1, "First Year"),
    SECONDYEAR(2, "Second Year"),
    THIRDYEAR(3, "Third Year"),
    FOURTHYEAR(4, "Fourth Year"),
	FIFTHYEAR(5, "Fifth Year"),
	SIXTHYEAR(6, "Sixth Year");
    
    private final int value;
	private final String description;
	
	CmsBatchEnum(int value, String description){
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
	
	public static CmsBatchEnum findById(int id) {
		CmsBatchEnum cmsBatchEnum = resolve(id);
		if (cmsBatchEnum == null) {
			throw new IllegalArgumentException("No matching constant for [" + id + "]");
		}
		return cmsBatchEnum;
	}
	
	public static CmsBatchEnum findByDescription(String description) {
		CmsBatchEnum cmsBatchEnum = resolve(description);
		if (cmsBatchEnum == null) {
			throw new IllegalArgumentException("No matching constant for [" + description + "]");
		}
		return cmsBatchEnum;
	}
	
	public static Batch createBatch(CmsBatchEnum bEnum) {
		Batch batch = new Batch();
		batch.setId(bEnum.id());
		if(bEnum.id() == 1) {
			batch.setBatch(BatchEnum.FIRSTYEAR);
		}else if(bEnum.id() == 2) {
			batch.setBatch(BatchEnum.SECONDYEAR);
		}else if(bEnum.id() == 3) {
			batch.setBatch(BatchEnum.THIRDYEAR);
		}else if(bEnum.id() == 4) {
			batch.setBatch(BatchEnum.FOURTHYEAR);
		}
		return batch;
	}
	
	@Nullable
	private static CmsBatchEnum resolve(int id) {
		for (CmsBatchEnum cmsBatchEnum : values()) {
			if (cmsBatchEnum.value == id) {
				return cmsBatchEnum;
			}
		}
		return null;
	}
	
	@Nullable
	private static CmsBatchEnum resolve(String description) {
		for (CmsBatchEnum cmsBatchEnum : values()) {
			if (cmsBatchEnum.description == description) {
				return cmsBatchEnum;
			}
		}
		return null;
	}
	
}
