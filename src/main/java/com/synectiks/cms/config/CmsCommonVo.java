package com.synectiks.cms.config;

public class CmsCommonVo {

	private String key;
	private Object value;
	
	public CmsCommonVo(String key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	public CmsCommonVo() {
		
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
