package com.synectiks.cms.graphql.types.AttendanceMaster;

import com.synectiks.cms.domain.AttendanceMaster;

public class AbstractAttendanceMasterPayload {
    private final AttendanceMaster attendanceMaster;

    public AbstractAttendanceMasterPayload(AttendanceMaster attendanceMaster) {
        this.attendanceMaster = attendanceMaster;
    }

	public AttendanceMaster getAttendanceMaster() {
		return attendanceMaster;
	}

	   
}
