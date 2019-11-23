package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.commons.entities.cms.CmsEmployeeVo;
import com.synectiks.commons.entities.cms.Employee;
import com.synectiks.commons.entities.cms.Vehicle;
import com.synectiks.cms.filter.employee.EmployeeListFilterInput;
import com.synectiks.cms.repository.EmployeeRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private CommonService commonService;

    public List<CmsEmployeeVo> searchEmployee(Long employeeId, Long vehicleId, String employeeName) throws Exception {
        Employee employee = new Employee();
        if (employeeId != null) {
            employee.setId(employeeId);
        }
        if (employeeName != null) ;
        {
            employee.setEmployeeName(employeeName);
        }

        if(vehicleId != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(vehicleId);
            employee.setVehicle(vehicle);
        }


        Example<Employee> example = Example.of(employee);
        List<Employee> list = this.employeeRepository.findAll(example);
        List<CmsEmployeeVo> ls = new ArrayList<>();
        for(Employee temp: list) {
//            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, temp.getPaymentDate());
            CmsEmployeeVo cev = CommonUtil.createCopyProperties(temp, CmsEmployeeVo.class);
            cev.setStrjoiningDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getJoiningDate()))));
            cev.setStrjobEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getJobEndDate()))));
            cev.setStrresignationDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getResignationDate()))));
            cev.setStrresignationAcceptanceDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getResignationAcceptanceDate()))));
            cev.setStrdrivingLicenceValidity(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getDrivingLicenceValidity()))));
            ls.add(cev);
        }
        return ls;
    }
    public List<CmsEmployeeVo> searchEmployee(EmployeeListFilterInput filter) throws Exception{
        Employee employee = new Employee();

        if(!CommonUtil.isNullOrEmpty(filter.getVehicleId())) {
           Vehicle vehicle = new Vehicle();
           if(vehicle != null){
               vehicle.setId(Long.valueOf(filter.getVehicleId()));
               employee.setVehicle(vehicle);
           }
        }

        if(!CommonUtil.isNullOrEmpty(filter.getEmployeeId())) {
            if (employee != null) {
                employee.setId(Long.valueOf(filter.getEmployeeId()));
            }
        }

        Example<Employee> example = Example.of(employee);
        List<Employee> list = this.employeeRepository.findAll(example);
        List<CmsEmployeeVo> ls = new ArrayList<>();
        for(Employee temp: list) {
//            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, temp.getPaymentDate());
            CmsEmployeeVo cev = CommonUtil.createCopyProperties(temp, CmsEmployeeVo.class);
            cev.setStrjoiningDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getJoiningDate()))));
            cev.setStrjobEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getJobEndDate()))));
            cev.setStrresignationDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getResignationDate()))));
            cev.setStrresignationAcceptanceDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getResignationAcceptanceDate()))));
            cev.setStrdrivingLicenceValidity(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(temp.getDrivingLicenceValidity()))));
            ls.add(cev);
        }
        return ls;
    }
    }
