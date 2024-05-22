package com.java.emsbackend.service;

import com.java.emsbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);

List<EmployeeDto> getAllEmployees();
EmployeeDto updateEmployee(Long employeeId,EmployeeDto updatedEmployee);
void deleteEmployee(Long employeeId);
}
