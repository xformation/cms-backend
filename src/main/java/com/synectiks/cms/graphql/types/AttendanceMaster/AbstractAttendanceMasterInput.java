package com.synectiks.cms.graphql.types.AttendanceMaster;

public class AbstractAttendanceMasterInput {
	private Long id;
    private String desc;
//    private Teach teach;
//    private Section section;
//    private AcademicYear academicYear;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/*public Teach getTeach() {
		return teach;
	}
	public void setTeach(Teach teach) {
		this.teach = teach;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}*/
	@Override
	public String toString() {
		return "AbstractAttendanceMasterInput [id=" + id + ", desc=" + desc + "]";
	}
	
	



    
}
