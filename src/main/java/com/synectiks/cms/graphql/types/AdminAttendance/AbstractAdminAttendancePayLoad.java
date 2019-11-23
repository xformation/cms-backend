package com.synectiks.cms.graphql.types.AdminAttendance;

import com.synectiks.commons.entities.cms.AdminAttendance;

public class AbstractAdminAttendancePayLoad {
    private final AdminAttendance adminAttendance;

    public AdminAttendance getAdminAttendance() {
        return adminAttendance;
    }

    public AbstractAdminAttendancePayLoad(AdminAttendance adminAttendance) {
        this.adminAttendance = adminAttendance;
    }
}
