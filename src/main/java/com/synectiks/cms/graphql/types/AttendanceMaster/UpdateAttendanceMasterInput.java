package com.synectiks.cms.graphql.types.AttendanceMaster;

public class UpdateAttendanceMasterInput extends AbstractAttendanceMasterInput{
	private Long teachId;
    private Long sectionId;
    private Long batchId;
	public Long getTeachId() {
		return teachId;
	}
	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchId == null) ? 0 : batchId.hashCode());
		result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
		result = prime * result + ((teachId == null) ? 0 : teachId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateAttendanceMasterInput other = (UpdateAttendanceMasterInput) obj;
		if (batchId == null) {
			if (other.batchId != null)
				return false;
		} else if (!batchId.equals(other.batchId))
			return false;
		if (sectionId == null) {
			if (other.sectionId != null)
				return false;
		} else if (!sectionId.equals(other.sectionId))
			return false;
		if (teachId == null) {
			if (other.teachId != null)
				return false;
		} else if (!teachId.equals(other.teachId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UpdateAttendanceMasterInput [teachId=" + teachId + ", sectionId=" + sectionId + ", batchId="
				+ batchId + "]";
	}
}
