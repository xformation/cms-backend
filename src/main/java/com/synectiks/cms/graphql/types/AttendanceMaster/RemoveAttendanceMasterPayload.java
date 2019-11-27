package com.synectiks.cms.graphql.types.AttendanceMaster;

import java.util.List;

import com.synectiks.cms.domain.AttendanceMaster;

public class RemoveAttendanceMasterPayload {
    private final List<AttendanceMaster> attendanceMaster;

    public RemoveAttendanceMasterPayload(List<AttendanceMaster> attendanceMaster) {
        this.attendanceMaster = attendanceMaster;
    }

	public List<AttendanceMaster> getAttendanceMaster() {
		return attendanceMaster;
	}
	

    
}
