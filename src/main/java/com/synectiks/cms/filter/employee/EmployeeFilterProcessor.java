package com.synectiks.cms.filter.employee;

import com.synectiks.cms.business.service.EmployeeService;
import com.synectiks.commons.entities.cms.CmsEmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeFilterProcessor {
    @Autowired
    private EmployeeService employeeService;
    public List<CmsEmployeeVo> searchEmployee(Long vehicleId, Long employeeId, String employeeName) throws Exception {
        return employeeService.searchEmployee(vehicleId,employeeId, employeeName);
    }

    public List<CmsEmployeeVo> searchEmployee(EmployeeListFilterInput filter) throws Exception {
        return employeeService.searchEmployee(filter);
    }
}
