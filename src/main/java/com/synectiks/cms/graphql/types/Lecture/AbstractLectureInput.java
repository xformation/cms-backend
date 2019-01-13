package com.synectiks.cms.graphql.types.Lecture;

import java.util.Date;

import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.enumeration.LecStatusEnum;

public class AbstractLectureInput {
    private Long id;
    private Date lecDate;
    private Date lastUpdatedOn;
    private String lastUpdatedBy;
    private LecStatusEnum lecStatus;
    private String desc;
//    private AttendanceMaster attendancemaster;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getLecDate() {
		return lecDate;
	}
	public void setLecDate(Date lecDate) {
		this.lecDate = lecDate;
	}
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public LecStatusEnum getLecStatus() {
		return lecStatus;
	}
	public void setLecStatus(LecStatusEnum lecStatus) {
		this.lecStatus = lecStatus;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/*public AttendanceMaster getAttendancemaster() {
		return attendancemaster;
	}
	public void setAttendancemaster(AttendanceMaster attendancemaster) {
		this.attendancemaster = attendancemaster;
	}*/
}
