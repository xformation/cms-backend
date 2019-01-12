package com.synectiks.cms.graphql.types.StudentAttendance;

import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.domain.enumeration.Status;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class AbstractStudentAttendanceInput {
    private Long id;
    private AttendanceStatusEnum attendanceStatus;
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

	public AttendanceStatusEnum getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatusEnum attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendanceStatus == null) ? 0 : attendanceStatus.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractStudentAttendanceInput other = (AbstractStudentAttendanceInput) obj;
		if (attendanceStatus != other.attendanceStatus)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractStudentAttendanceInput [id=" + id + ", attendanceStatus=" + attendanceStatus + ", comments="
				+ comments + "]";
	}

    
}
