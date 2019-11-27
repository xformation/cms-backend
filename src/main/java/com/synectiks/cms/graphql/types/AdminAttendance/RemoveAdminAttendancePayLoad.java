package com.synectiks.cms.graphql.types.AdminAttendance;

import com.synectiks.cms.domain.AdminAttendance;

import java.util.List;

public class RemoveAdminAttendancePayLoad {

    private final List<AdminAttendance> adminAttendance;

    public RemoveAdminAttendancePayLoad(List<AdminAttendance> adminAttendance) {
        this.adminAttendance = adminAttendance;
    }

    public List<AdminAttendance> getAdminAttendance() {
        return adminAttendance;
    }
}
