package com.synectiks.cms.entities;

import java.io.Serializable;
import java.util.List;

/**
 * A Vo for the various dashboards.
 */
public class CmsDashboardVo implements Serializable {

	private Student student;
	private Teacher teacher;
	private CmsInvoice cmsInvoice;
	private List<StudentFacilityLink> studentFacilityLinkList;
	private List<CmsLectureVo> cmsLectureVoList;
	private List<CmsNotificationsVo> cmsNotificationsVoList;
	private List<CmsHolidayVo> cmsHolidayVoList;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public CmsInvoice getCmsInvoice() {
		return cmsInvoice;
	}
	public void setCmsInvoice(CmsInvoice cmsInvoice) {
		this.cmsInvoice = cmsInvoice;
	}
	public List<StudentFacilityLink> getStudentFacilityLinkList() {
		return studentFacilityLinkList;
	}
	public void setStudentFacilityLinkList(List<StudentFacilityLink> studentFacilityLinkList) {
		this.studentFacilityLinkList = studentFacilityLinkList;
	}
	public List<CmsLectureVo> getCmsLectureVoList() {
		return cmsLectureVoList;
	}
	public void setCmsLectureVoList(List<CmsLectureVo> cmsLectureVoList) {
		this.cmsLectureVoList = cmsLectureVoList;
	}
	public List<CmsNotificationsVo> getCmsNotificationsVoList() {
		return cmsNotificationsVoList;
	}
	public void setCmsNotificationsVoList(List<CmsNotificationsVo> cmsNotificationsVoList) {
		this.cmsNotificationsVoList = cmsNotificationsVoList;
	}
	public List<CmsHolidayVo> getCmsHolidayVoList() {
		return cmsHolidayVoList;
	}
	public void setCmsHolidayVoList(List<CmsHolidayVo> cmsHolidayVoList) {
		this.cmsHolidayVoList = cmsHolidayVoList;
	}
    
}
