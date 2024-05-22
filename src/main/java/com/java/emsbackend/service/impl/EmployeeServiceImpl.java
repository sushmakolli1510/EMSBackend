package com.java.emsbackend.service.impl;

import com.java.emsbackend.dto.EmployeeDto;
import com.java.emsbackend.entity.Employee;
import com.java.emsbackend.exception.ResourceNotFoundException;
import com.java.emsbackend.mapper.EmployeeMapper;
import com.java.emsbackend.repository.EmployeeRepository;
import com.java.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class EmployeeServiceImpl
        implements EmployeeService{
@Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow((()->new ResourceNotFoundException("Employee not exist for given id : "+employeeId)));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();

        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given id: "+employeeId));
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
                employee.setEmail(updatedEmployee.getEmail());
                Employee updatedEmployeeObj=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee=employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee is not exists with given id: "+employeeId));
        employeeRepository.deleteById(employeeId);

    }


}
