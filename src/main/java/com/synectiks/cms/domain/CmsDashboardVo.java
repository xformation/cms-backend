package com.synectiks.cms.domain;

import java.io.Serializable;
import java.util.List;

/**
 * A Vo for the AcademicYear entity.
 */
public class CmsDashboardVo implements Serializable {

	private Student student;
	private Teacher teacher;
	private CmsInvoice cmsInvoice;
	private List<StudentFacilityLink> studentFacilityLinkList;
	private List<CmsLectureVo> cmsLectureVoList;
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
    
}
