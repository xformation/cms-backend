package com.synectiks.cms.graphql.types.AdminAttendance;

import java.util.List;

import com.synectiks.cms.entities.AdminAttendance;

public class RemoveAdminAttendancePayLoad {

    private final List<AdminAttendance> adminAttendance;

    public RemoveAdminAttendancePayLoad(List<AdminAttendance> adminAttendance) {
        this.adminAttendance = adminAttendance;
    }

    public List<AdminAttendance> getAdminAttendance() {
        return adminAttendance;
    }
}
