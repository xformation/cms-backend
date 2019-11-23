package com.synectiks.cms.graphql.types.AttendanceMaster;

import com.synectiks.commons.entities.cms.AttendanceMaster;

public class AbstractAttendanceMasterPayload {
    private final AttendanceMaster attendanceMaster;

    public AbstractAttendanceMasterPayload(AttendanceMaster attendanceMaster) {
        this.attendanceMaster = attendanceMaster;
    }

	public AttendanceMaster getAttendanceMaster() {
		return attendanceMaster;
	}

	   
}
